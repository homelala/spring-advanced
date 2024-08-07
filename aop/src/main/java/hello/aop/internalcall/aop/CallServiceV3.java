
package hello.aop.internalcall.aop;

import hello.aop.internalcall.InternalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
/*
구조를 분리
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV3 {
    private final InternalService internalService;

    public void external() {
        log.info("external");
        internalService.internal();
    }
}
