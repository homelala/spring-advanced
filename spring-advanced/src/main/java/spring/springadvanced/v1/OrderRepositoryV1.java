package spring.springadvanced.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.springadvanced.hellotrace.HelloTraceV1;
import spring.springadvanced.trace.TraceStatus;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin(this.getClass().getName());
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
