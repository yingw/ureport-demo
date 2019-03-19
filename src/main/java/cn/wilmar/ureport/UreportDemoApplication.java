package cn.wilmar.ureport;

import io.sentry.spring.SentryServletContextInitializer;
import org.springframework.boot.SpringApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * @author yinguowei
 */
@ImportResource("classpath:context.xml")
@EnableJpaAuditing
@SpringBootApplication
public class UreportDemoApplication {
    Logger logger = LoggerFactory.getLogger(UreportDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UreportDemoApplication.class, args);
    }


    /**
     * 异常处理器控制将异常转发送给 Sentry
     */
    @Bean
    public HandlerExceptionResolver sentryExceptionResolver() {
        return new io.sentry.spring.SentryExceptionResolver();
    }

    /**
     * 监听器使 Sentry 可以包含 HTTP 请求信息
     */
    @Bean
    public ServletContextInitializer sentryServletContextInitializer() {
        return new SentryServletContextInitializer();
    }


}
