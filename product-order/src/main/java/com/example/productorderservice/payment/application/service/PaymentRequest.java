package com.example.productorderservice.payment.application.service;

import org.springframework.util.Assert;
// 결제 요청
public record PaymentRequest(Long orderId, String cardNumber) {
    //결제 요청에 필요한 것들 요청하기 - 만족 못하면 예외발생 / 문구 출력
    public PaymentRequest {
        Assert.notNull(orderId, "주문 ID는 필수입니다.");
        Assert.hasText(cardNumber, "카드 번호는 필수입니다.");
    }
}
