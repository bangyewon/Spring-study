package com.example.productorderservice.payment.application.service;

import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.payment.application.port.PaymentPort;
import com.example.productorderservice.payment.domain.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments") // url의 payments의 엔드포인트에 매핑됨
class PaymentService {
    private final PaymentPort paymentPort;
    //생성자
    PaymentService(final PaymentPort paymentPort) {
        this.paymentPort = paymentPort;
    }

    @PostMapping
    @Transactional
    //payment() : post요청 처리 -> /payment로 부터 결제요청 받음 -> JSON 데이터 PaymentRequest 객체로 매핑
    public ResponseEntity<Void> payment(@RequestBody final PaymentRequest request) {
        final Order order = paymentPort.getOrder(request.orderId());
        //결제 생성,주문 정보,카드 번호 이용해 payment 객체 생성
        final Payment payment = new Payment(order, request.cardNumber());
        //결제 처리 -> PaymentPort로 요청하기 / 외부 결제 시스템,통신 담당
        paymentPort.pay(payment.getPrice(), payment.getCardNumber());
        paymentPort.save(payment);

        return ResponseEntity.status(HttpStatus.OK).build(); //성공상태 코드 반환
    }

}
