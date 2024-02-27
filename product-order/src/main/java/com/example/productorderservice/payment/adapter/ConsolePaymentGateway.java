package com.example.productorderservice.payment.adapter;

import org.springframework.stereotype.Component;
//결제 처리하기
@Component
public class ConsolePaymentGateway implements PaymentGateway {
    // 총 결제 금액과 카드번호 받아 결제 처리하기
    // 콘솔창으로 결제완료 메세지
    @Override
    public void execute(int totalPrice, String cardNumber) {
        System.out.println("결제 완료");
    }
}
