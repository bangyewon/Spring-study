package hello.core.member;
// MemberService 구현체
//Impl이라고 붙인 이유 : 구현체가 하나만 있을때 인터페이스 명 뒤에 Impl이라고 붙이는 관례
public class MemberServiceImpl implements MemberService {
    //구현객체 선택해 줘야함
    private  final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 다형성에 의해서 join을 호출하게 되면 MemoryMemberRepository의 인터페이스가 아닌 해당  MemoryMemberRepository에 있는 save 호출됨
     * 오버라이드 한 것이 호출되는 것
     */
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
