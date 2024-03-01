package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

// 주문 서비스 -> 할인 관련 구현하기
//해당 인터페이스는 OrderService 의존 / MemberRepository,DiscountPolicy 참조 하고 있음
public class OrderServiceimpl implements OrderService {
    //고정 할인 정책의 memberRepository -> member 확인해야하기에
//    private final MemberRepository memberRepository = new MemoryMemberRepository();


    //고정 할인 정책
//    private final DiscountPolicy discuntPolicy = new FixDiscountPolicy();
    //바뀐 할인 정책으로 고치기
//        private final DiscountPolicy discuntPolicy = new RateDiscountPolicy();
    //하지만 의존성 문제로 dip위반하기에 다시 바꿈
    private final MemberRepository memberRepository;
    private DiscountPolicy discountPolicy; // 하지만 에러나요 ..-> 구체 객체가 없기때문에

    //생성자 만들어주기
    //AppConfig와 연결해 생성자 주입 완료 시키기
    public OrderServiceimpl(MemberRepository memberRepository,DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     *주문 생성 요청 오면
     * 1. 회원 정보 먼저 조회해 grade 판단
     * 2. 할인 정책에 회원 넘기기 - 등급만 넘겨도 되지만 확장성 등 고려해 member 통채로 넘김
     * 3. 최종적으로 할인된 가격 받기 : discountPrice
     * 4. 리턴 값으로 할인된 주문 값 받게됨
     */
    @Override
    public Order createOrder(Long memberId,String itemName,int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
