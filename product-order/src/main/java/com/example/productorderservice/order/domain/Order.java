package com.example.productorderservice.order.domain;

import com.example.productorderservice.product.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *@Entity :  JPA 엔티티 나타냄 -> 데베에 매핑되 영구저장 가능
 * @NoArgsConstructor(access = AccessLevel.PROTECTED) : 인자없는 기본 생성자 생성 -> 외부 직접호출x
 */
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter // getter 메서드 생성
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne // 일대일관계
    private Product product;
    private int quantity;

    //생성자
    public Order(final Product product, final int quantity) {
        // 필수조건
        Assert.notNull(product, "상품은 필수입니다.");
        Assert.isTrue(quantity > 0, "수량은 0보다 커야 합니다.");
        this.product = product;
        this.quantity = quantity;
    }
    //총 주문가격 계산
    public int getTotalPrice() {
        return product.getDiscountedPrice() * quantity;
    }
}
