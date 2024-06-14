package spring.springadvanced.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.springadvanced.trace.LogTrace;
import spring.springadvanced.trace.TraceStatus;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin(this.getClass().getName());
            orderRepository.save(itemId);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
