package spring.springadvanced.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.springadvanced.trace.LogTrace;
import spring.springadvanced.trace.template.AbstractTemplate;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId){
        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call() {
                if (itemId.equals("ex")){
                    throw new IllegalStateException("예외 발생");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute(this.getClass().getName());
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
