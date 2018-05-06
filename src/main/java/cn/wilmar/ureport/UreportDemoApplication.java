package cn.wilmar.ureport;

import io.sentry.Sentry;
import io.sentry.spring.SentryExceptionResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * @author yinguowei
 */
@ImportResource("classpath:context.xml")
@EnableJpaAuditing
@SpringBootApplication
@Controller
public class UreportDemoApplication {
    Logger logger = LoggerFactory.getLogger(UreportDemoApplication.class);

    @Bean HandlerExceptionResolver sentryExceptionResolver() {
//        Sentry.init("");
        return new SentryExceptionResolver();
    }

    @RequestMapping("/test")
    @ResponseBody
    String home() {
        try {
            int x = 1 / 0;
        } catch (RuntimeException e) {
            Sentry.capture(e);
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(UreportDemoApplication.class, args);
    }
}
