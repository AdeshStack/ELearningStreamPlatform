package com.course.service.course_service.dao;

import java.util.List;

public record courseVideoDto (Long courseId, List<String> videoUrls) {
}
