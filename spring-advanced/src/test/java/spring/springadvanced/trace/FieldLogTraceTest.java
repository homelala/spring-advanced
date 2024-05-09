package spring.springadvanced.trace;

import org.junit.jupiter.api.Test;

class FieldLogTraceTest {

    FieldLogTrace trace = new FieldLogTrace();

    @Test
    void begin_end_level() {
        TraceStatus status1 = trace.begin("test1");
        TraceStatus status2 = trace.begin("test2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        TraceStatus status1 = trace.begin("test1");
        TraceStatus status2 = trace.begin("test2");
        trace.exception(status2, new IllegalArgumentException());
        trace.exception(status1, new IllegalArgumentException());
    }
}