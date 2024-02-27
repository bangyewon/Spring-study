package com.example.productorderservice.order.application.port;

import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.product.domain.Product;
// 주문 서비스 포트 정의
public interface OrderPort {
    Product getProductById(final Long productId); // ID 갖고오기

    void save(final Order order); // 주문저장
}
