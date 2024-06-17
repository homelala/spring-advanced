package spring.springadvanced.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.springadvanced.trace.LogTrace;
import spring.springadvanced.trace.TraceStatus;
import spring.springadvanced.trace.template.AbstractTemplate;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    @GetMapping("/v4/request")
    public String orderItem(String itemId){
        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "OK";
            }
        };
        return template.execute(this.getClass().getName());
    }
}
