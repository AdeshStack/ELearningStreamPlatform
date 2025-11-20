package com.course.service.course_service.dao;

import java.util.List;

public record CourseDto(Long id, String title, String description, Long authorId, UserSummaryDto author) {}