package spring.springadvanced.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallBackTest {

    @Test
    void CallBackTestV1() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(new CallBack() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1 수행");
            }
        });

        template.execute(new CallBack() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2 수행");
            }
        });
    }

    @Test
    void CallBackTestV2() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> log.info("비즈니스 로직 1 수행"));
        template.execute(() -> log.info("비즈니스 로직 2 수행"));
    }
}
