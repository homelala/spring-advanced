package spring.springadvanced.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.springadvanced.hellotrace.HelloTraceV2;
import spring.springadvanced.trace.TraceStatus;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String orderItem(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin(this.getClass().getName());
            orderService.orderItem(status.getTraceId(), itemId);
            trace.end(status);
            return "ok";
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
