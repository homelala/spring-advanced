
package hello.aop.internalcall.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {
    //    private final ApplicationContext applicationContext;
    private final ObjectProvider<CallServiceV2> callServiceV2ObjectProvider;

    public CallServiceV2(ApplicationContext applicationContext, ObjectProvider<CallServiceV2> callServiceV2ObjectProvider) {
        this.callServiceV2ObjectProvider = callServiceV2ObjectProvider;
    }


    public void external() {
        log.info("external");
        CallServiceV2 callServiceV2 = callServiceV2ObjectProvider.getObject();
        callServiceV2.internal();
    }

    public void internal() {
        log.info("internal");
    }

}
