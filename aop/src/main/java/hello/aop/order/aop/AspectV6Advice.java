package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {
//    @Around("hello.aop.order.aop.PointCut.allOrder()") // 포인트 컷
//    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable { // 어드바이스
//        log.info("log -> {}", joinPoint.getSignature());
//        return joinPoint.proceed();
//    }
//
    // hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service인 것
    @Around("hello.aop.order.aop.PointCut.orderAndService()") // 포인트 컷
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable { // 어드바이스
        try {
            //@Before
            log.info("트랜잭션 시작 -> {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("트랜잭션 종료 -> {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("트랜잭션 롤백 -> {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("리소스 릴리즈 -> {}", joinPoint.getSignature());
        }
    }

    @Before("hello.aop.order.aop.PointCut.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("doBefore -> {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "hello.aop.order.aop.PointCut.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("doReturn -> {} return -> {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "hello.aop.order.aop.PointCut.orderAndService()", throwing = "ex")
    public void doThrow(JoinPoint joinPoint, Exception ex) {
        log.info("doThrow -> {} exception -> {}", joinPoint.getSignature(), ex.getMessage());
    }

    @After("hello.aop.order.aop.PointCut.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("doAfter -> {}", joinPoint.getSignature());
    }
}
