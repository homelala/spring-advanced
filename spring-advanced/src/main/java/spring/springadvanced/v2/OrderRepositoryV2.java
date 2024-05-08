package spring.springadvanced.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.springadvanced.hellotrace.HelloTraceV2;
import spring.springadvanced.trace.TraceId;
import spring.springadvanced.trace.TraceStatus;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId){
        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId, this.getClass().getName());
            if (itemId.equals("ex")){
            throw new IllegalStateException("예외 발생");
        }
        sleep(1000);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
