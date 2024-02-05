package com.example.productorderservice.payment.domain;

import com.example.productorderservice.order.domain.Order;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
//결제 나타내는 도메인 클래스
@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 식별자 지정 -> 자동생성되도록
    private Long id;
    @OneToOne // order,cardNumber은 일대일관계
    private Order order;
    private String cardNumber;
    //생성자
    public Payment(final Order order, final String cardNumber) {
        //필수 요건
        Assert.notNull(order, "주문은 필수입니다.");
        Assert.hasText(cardNumber, "카드 번호는 필수입니다.");
        this.order = order;
        this.cardNumber = cardNumber;
    }
    // 총 가격 계산 반환 메서드
    public int getPrice() {
        return order.getTotalPrice();
    }
}
