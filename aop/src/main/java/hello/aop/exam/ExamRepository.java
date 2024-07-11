package hello.aop.exam;

import hello.aop.exam.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {
    private static int seq = 0;

    @Trace
    public String save(String itemId) throws IllegalAccessException {
        seq++;
        if (seq % 5 == 0) {
            throw new IllegalAccessException("예외 발생");
        }
        return "ok";
    }
}
