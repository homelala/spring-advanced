package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

    @Test
    void basicConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        // A는 빈으로 등록
        A beanA = applicationContext.getBean("BeanA", A.class);
        beanA.hello();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean("BeanB", B.class));
    }

    @Slf4j
    @Configuration
    static class BasicConfig {
        @Bean(name = "BeanA")
        public A a() {
            return new A();
        }
    }

    @Slf4j
    static class A{
        void hello() {
            log.info("helloA");
        }
    }

    @Slf4j
    static class B{
        void hello() {
            log.info("helloB");
        }
    }
}
