package com.cart.service.cart_service.dao;

import lombok.Builder;

import java.util.List;

@Builder
public record CartResponse(List<Long> course) {
}
