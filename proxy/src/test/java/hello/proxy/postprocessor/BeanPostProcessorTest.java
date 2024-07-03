package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessorTest {

    @Test
    void basicConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

        // A는 빈으로 등록
        B beanB = applicationContext.getBean("BeanA", B.class);
        beanB.hello();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(A.class));
    }

    @Slf4j
    @Configuration
    static class BeanPostProcessorConfig {
        @Bean(name = "BeanA")
        public A a() {
            return new A();
        }

        @Bean
        public AToBPostProcessor helloPostProcessor() {
            return new AToBPostProcessor();
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

    @Slf4j
    static class AToBPostProcessor  implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) {
            log.info("beanName = {}", beanName);
            if (bean instanceof A) {
                return new B();
            }
            return bean;
        }
    }
}
