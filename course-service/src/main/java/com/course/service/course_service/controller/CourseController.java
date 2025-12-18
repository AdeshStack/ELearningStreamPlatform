package com.course.service.course_service.controller;
import com.course.service.course_service.dao.CourseDto;
import com.course.service.course_service.dao.CreateCourseRequest;
import com.course.service.course_service.dao.courseVideoDto;
import com.course.service.course_service.entity.Course;
import com.course.service.course_service.repository.CourseRepository;
import com.course.service.course_service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private  CourseRepository courseRepository;

    private final CourseService service;
    public CourseController(CourseService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<CourseDto> create(@RequestBody CreateCourseRequest req) {
        Course c = Course.builder()
                .title(req.title())
                .description(req.description())
                .authorId(req.authorId())
                .build();
        Course saved = service.createCourse(c);
        return ResponseEntity.ok(service.toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> get(@PathVariable Long id) {
        var course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return ResponseEntity.ok(service.toDto(course));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<Page<CourseDto>> listByAuthor(@PathVariable("authorId") Long authorId, Pageable pageable) {
        Page<Course> page = service.listCoursesByAuthor(authorId, pageable);
        Page<CourseDto> dtoPage = page.map(service::toDto);
        return ResponseEntity.ok(dtoPage);
    }
    @PostMapping("/{courseId}/videos")
    public ResponseEntity<String> addVideoToCourse(@PathVariable Long courseId, @RequestParam String videoUrl) {
        service.addVideoToCourse(courseId, videoUrl);
        return ResponseEntity.ok("Video added to course");
    }

    @GetMapping("/videos/{courseId}")
    public ResponseEntity<courseVideoDto> getVideosByCourseId(@PathVariable ("courseId") Long courseId) {

                return ResponseEntity.ok(this.service.VideoToDto(courseId));
    }

}
