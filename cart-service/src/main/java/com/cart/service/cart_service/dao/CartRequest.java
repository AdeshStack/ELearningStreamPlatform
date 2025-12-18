package com.cart.service.cart_service.dao;

import lombok.Builder;

@Builder
public record CartRequest(Long userId, Long courseId) {
}
