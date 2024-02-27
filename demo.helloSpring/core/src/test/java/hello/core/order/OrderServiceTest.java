package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hello.core.member.Grade.VIP;
//OrderApp으로 메인메소드 하는 것 보다 테스트로 하는게 낫다!
//단일 테스트로 하는게 나음(순수 자바코드)
public class OrderServiceTest {
//            MemberService memberService = new MemberServiceImpl();
//            OrderService orderService = new OrderServiceimpl();
MemberService memberService;
OrderService orderService;
    @BeforeEach // test실행 전 무조건 실행되는 것
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = getOrderService(appConfig);
    }

    private static OrderService getOrderService(AppConfig appConfig) {
        return appConfig.orderService();
    }

    @Test
    void createOrder() {
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    }

