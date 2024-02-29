package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회 : 호출시마다 객체 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회 : 호출시마다 객체 생성
        MemberService memberService2 = appConfig.memberService();
        //참조값이 다른 것을 확인
//        System.out.println("memberService1 =" + memberService1);
//        System.out.println("memberService2 =" + memberService2); //호출시마다 객체 생성되는 건 좋지 않음

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1" + singletonService1);
        System.out.println("singletonService2" + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
        //isSameAs : 인스턴스와 같은 것을 비교함
        // equl : java의 equls 메소드 오버라이드 비교함

    }
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        //조회할때마다 갖고있는 것 반환하고있음
        //참조값 다름
        System.out.println("memberSerivce1 = " + memberService1);
        System.out.println("memberSerivce2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
