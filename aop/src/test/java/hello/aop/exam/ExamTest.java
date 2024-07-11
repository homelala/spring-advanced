package hello.aop.exam;

import hello.aop.exam.aop.TraceAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Slf4j
@Import(TraceAspect.class)
public class ExamTest {

    @Autowired
    ExamService examService;

    @Test
    void test() throws IllegalAccessException {
        for (int i = 0; i < 10; i++) {
            log.info("examService.request -> {}", i);
            examService.request("item" + i);
        }
    }
}
