package com.example.productorderservice.payment.adapter;

import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.order.adapter.OrderRepository;
import com.example.productorderservice.payment.domain.Payment;
import com.example.productorderservice.payment.application.port.PaymentPort;
import org.springframework.stereotype.Component;
//결제 관련 기능
//@Component : 스프링에서 해당 클래스가 관리되는 빈이라는 것 나타냄
@Component
class PaymentAdapter implements PaymentPort {
    //세게의 인스턴스 주입 -> 외부 결제 / 결제 정보 저장소 / 주문 정보 저장소 연동
    private final PaymentGateway paymentGateway;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    //생성자
    PaymentAdapter(final PaymentGateway paymentGateway, final PaymentRepository paymentRepository, final OrderRepository orderRepository) {
        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrder(final Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));
    }

    @Override
    public void pay(final int totalPrice, final String cardNumber) {
        paymentGateway.execute(totalPrice, cardNumber);
    }

    @Override
    public void save(final Payment payment) {
        paymentRepository.save(payment);
    }
}
