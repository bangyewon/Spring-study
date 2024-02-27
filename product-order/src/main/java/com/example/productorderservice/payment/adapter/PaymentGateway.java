package com.example.productorderservice.payment.adapter;

//외부결제
interface PaymentGateway {
    // 총 가격과 카드번호 인자로 받기
    void execute(int totalPrice, String cardNumber);

}
