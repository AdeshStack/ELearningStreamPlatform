package com.course.service.course_service.service;


import com.course.service.course_service.dao.CourseDto;
import com.course.service.course_service.dao.UserSummaryDto;
import com.course.service.course_service.dao.courseVideoDto;
import com.course.service.course_service.entity.Course;
import com.course.service.course_service.external.UserClient;
import com.course.service.course_service.repository.CourseRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository repo;

    private final UserClient userClient;

    public CourseService(CourseRepository repo, UserClient userClient) {
        this.repo = repo;
        this.userClient = userClient;
    }

    public Course createCourse(Course course) {
        return repo.save(course);
    }

    public Page<Course> listCoursesByAuthor(Long authorId, Pageable pageable) {
        return repo.findByAuthorId(authorId, pageable);
    }

    // when building DTO we call user-service //If user-service is slow, down, or throwing errors → your service would also become slow or fail.
    //A circuit breaker prevents that.
    @CircuitBreaker(name = "userService", fallbackMethod = "authorFallback")
    public UserSummaryDto getAuthorSummary(Long authorId) {
        var u = userClient.getUserById(authorId);
        if (u == null) return new UserSummaryDto(authorId, "Unknown", null);
        return new UserSummaryDto(authorId, u.name(), u.email());
    }

    // fallback signature must match (params + Throwable)
    public UserSummaryDto authorFallback(Long authorId, Throwable t) {
        return new UserSummaryDto(authorId, "Unknown", null);
    }

    public CourseDto toDto(Course c) {
        UserSummaryDto author = getAuthorSummary(c.getAuthorId());
        return new CourseDto(c.getId(), c.getTitle(), c.getDescription(), c.getAuthorId(), author);
    }


    public void addVideoToCourse(Long courseId, String videoUrl) {
        Course course = repo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.getVideos().add(videoUrl);
        repo.save(course);
    }
    public courseVideoDto VideoToDto(Long courseId) {

        Course course = repo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return new courseVideoDto(courseId, course.getVideos());
    }
}
