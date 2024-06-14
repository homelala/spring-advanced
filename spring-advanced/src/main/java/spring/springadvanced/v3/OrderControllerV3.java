package spring.springadvanced.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.springadvanced.hellotrace.HelloTraceV2;
import spring.springadvanced.trace.LogTrace;
import spring.springadvanced.trace.TraceStatus;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String orderItem(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin(this.getClass().getName());
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
