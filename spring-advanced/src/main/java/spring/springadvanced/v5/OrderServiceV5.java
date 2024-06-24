package spring.springadvanced.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.springadvanced.trace.LogTrace;
import spring.springadvanced.trace.callback.TraceCallBack;
import spring.springadvanced.trace.callback.TraceTemplate;
import spring.springadvanced.trace.template.AbstractTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;

    public void orderItem(String itemId) {
        template.execute(this.getClass().getName(), (TraceCallBack<Void>) () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
