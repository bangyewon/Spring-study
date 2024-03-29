package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy // 직접 만든 애노테이션 붙여주기
public class RateDiscountPolicy implements DiscountPolicy {
    private  int discountPercent = 10;

    @Override
    public int discount(Member member,int price) {
        if(member.getGrade() == Grade.VIP) {
            return price*discountPercent / 100;
        }else {
            return 0;
        }
    }
}
