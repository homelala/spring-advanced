package spring.springadvanced.v5;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;
import spring.springadvanced.trace.LogTrace;
import spring.springadvanced.trace.callback.TraceCallBack;
import spring.springadvanced.trace.callback.TraceTemplate;
import spring.springadvanced.trace.template.AbstractTemplate;

@Repository
public class OrderRepositoryV5 {


    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId){
        template.execute(this.getClass().getName(), (TraceCallBack<Void>) () -> {
            if (itemId.equals("ex")){
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
