package com.example.productorderservice.order.application.service;

import org.springframework.util.Assert;
// 주문 생성 요청 나타내는 createOrderRequest 정의하기
public record CreateOrderRequest(Long productId, int quantity) {
    // 주문 수량 상품 ID 요건
    //NULL이면 안됨 -> NULL이면 예외 발생
    public CreateOrderRequest {
        Assert.notNull(productId, "상품 ID는 필수입니다.");
        Assert.isTrue(quantity > 0, "수량은 0보다 커야 합니다.");
    }

}
