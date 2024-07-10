package hello.aop.pointcut;

import hello.aop.member.annotation.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(AtTargetWithinTest.Config.class)
@SpringBootTest
public class AtTargetWithinTest {
    @Autowired
    Child child;

    @Test
    void success() {
        log.info("child -> {}", child.getClass());
        child.childMethod();
        child.parentMethod();
    }

    static class Config {
        @Bean
        public Child child() {
            return new Child();
        }

        @Bean
        public Parent parent() {
            return new Parent();
        }

        @Bean
        public AtTargetWithinAspect atTargetWithinAspect() {
            return new AtTargetWithinAspect();
        }
    }

    static class Parent {
        public void parentMethod() {

        }
    }

    @ClassAop
    static class Child extends Parent {
        public void childMethod() {

        }
    }

    @Slf4j
    @Aspect
    static class AtTargetWithinAspect {

        //@Target: 인스턴스 기준으로 모든 메서드와 조인 프인트를 선정, 부모 타입의 메서드도 적용
        @Around("execution(* hello.aop..*(..)) && @target(hello.aop.member.annotation.ClassAop)")
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("atTarget -> {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        //@Witnin: 선택된 클래스 내부에 있는 메서드기준으로 선정, 부모 메서드 적용 안됨
        @Around("execution(* hello.aop..*(..)) && @within(hello.aop.member.annotation.ClassAop)")
        public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("atWitnin -> {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
