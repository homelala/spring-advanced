package spring.springadvanced;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.springadvanced.trace.FieldLogTrace;
import spring.springadvanced.trace.LogTrace;
import spring.springadvanced.trace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }
}
