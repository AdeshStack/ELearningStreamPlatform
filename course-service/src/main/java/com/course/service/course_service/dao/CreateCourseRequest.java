package com.course.service.course_service.dao;

public record CreateCourseRequest(String title, String description, Long authorId ) {}