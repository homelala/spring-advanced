package spring.springadvanced.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.springadvanced.trace.LogTrace;
import spring.springadvanced.trace.callback.TraceCallBack;
import spring.springadvanced.trace.callback.TraceTemplate;
import spring.springadvanced.trace.template.AbstractTemplate;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String orderItem(String itemId){
        return template.execute(this.getClass().getName(), () -> {
            orderService.orderItem(itemId);
            return "OK";
        });
    }
}
