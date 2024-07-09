package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCut {
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder() {
    } // 포인트컷 signature

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {
    }

    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
}
