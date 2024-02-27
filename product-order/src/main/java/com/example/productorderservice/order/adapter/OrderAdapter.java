package com.example.productorderservice.order.adapter;

import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.order.application.port.OrderPort;
import com.example.productorderservice.product.domain.Product;
import com.example.productorderservice.product.adapter.ProductRepository;
import org.springframework.stereotype.Component;

//주문 관련 기능 구현
//주문
@Component
class OrderAdapter implements OrderPort {
    //외부 종속성 - ProductRepository,OrderRepository
    //상품과 주문에 대한 데이터 엑세스 가능
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    //생성자
    private OrderAdapter(final ProductRepository productRepository, final OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    //상품 ID 기반 상품조회
    @Override
    public Product getProductById(final Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
    }
    // 주문 저장 -> orderRepository로 데베 저장
    @Override
    public void save(final Order order) {
        orderRepository.save(order);
    }
}
