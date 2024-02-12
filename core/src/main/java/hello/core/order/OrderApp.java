package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

import static hello.core.member.Grade.VIP;
//메인메소드로 하는 것은 좋은 방법이 아님
public class OrderApp {
     public  static void main(String[] args) {
         AppConfig appConfig = new AppConfig();
         MemberService memberService = appConfig.memberService();
         OrderService orderService = appConfig.orderService();
//         MemberService memberService = new MemberServiceImpl(null);
//         OrderService orderService = new OrderServiceimpl(null,null);

         Long memberId = 1L;
         Member member = new Member(memberId, "memberA", Grade.VIP);
         memberService.join(member);

         Order order = orderService.createOrder(memberId,"itemA",10000);

         System.out.println("order =" + order);

     }
}
