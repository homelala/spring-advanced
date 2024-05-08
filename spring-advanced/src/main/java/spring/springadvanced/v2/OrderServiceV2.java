package spring.springadvanced.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.springadvanced.hellotrace.HelloTraceV2;
import spring.springadvanced.trace.TraceId;
import spring.springadvanced.trace.TraceStatus;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId){
        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId, this.getClass().getName());
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
