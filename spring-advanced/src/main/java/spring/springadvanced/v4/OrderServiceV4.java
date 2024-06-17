package spring.springadvanced.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.springadvanced.trace.LogTrace;
import spring.springadvanced.trace.TraceStatus;
import spring.springadvanced.trace.template.AbstractTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId){
        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };
        template.execute(this.getClass().getName());
    }
}
