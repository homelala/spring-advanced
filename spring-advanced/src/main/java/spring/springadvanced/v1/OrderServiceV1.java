package spring.springadvanced.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.springadvanced.hellotrace.HelloTraceV1;
import spring.springadvanced.trace.TraceId;
import spring.springadvanced.trace.TraceStatus;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepositoryV1;
    private final HelloTraceV1 trace;

    public void orderItem(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin(this.getClass().getName());
            orderRepositoryV1.save(itemId);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
