package com.example.productorderservice.order.application.service;

import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.order.application.port.OrderPort;
import com.example.productorderservice.product.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//RESTful API로 주문 생성 위한 클래스

/**
 * @RestController : 스프링에 해당 클래스 REST 컨트롤러 알려줌
 * @RequestMapping("/orders") : -> /orders 엔드포인트 매핑됨 나타냄
 */
@RestController
@RequestMapping("/orders")
public
class OrderService {
    private final OrderPort orderPort;
    // 생성자
    OrderService(final OrderPort orderPort) {
        this.orderPort = orderPort;
        //orderProt 인터페이스 주입 -> 의존성 분리
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createOrder(
            @RequestBody final CreateOrderRequest request) {
        //상품ID 갖고와서 상품정보 갖고옴
        final Product product = orderPort.getProductById(request.productId());
        // 주문 생성, 상품 수량정보 사용 -> order 객체 생성
        final Order order = new Order(product, request.quantity());

        orderPort.save(order);
        // 주문 성공적으로 생성 코드 반환
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
