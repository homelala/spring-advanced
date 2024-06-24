package spring.springadvanced.trace.callback;

import org.springframework.stereotype.Component;
import spring.springadvanced.trace.LogTrace;
import spring.springadvanced.trace.TraceStatus;

@Component
public class TraceTemplate {
    private final LogTrace trace;


    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public <T> T execute(String message, TraceCallBack<T> callBack) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);
            T result = callBack.call();
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
