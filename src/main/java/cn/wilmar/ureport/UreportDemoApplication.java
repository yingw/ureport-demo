package cn.wilmar.ureport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author yinguowei
 */
@ImportResource("classpath:context.xml")
@EnableJpaAuditing
@SpringBootApplication
public class UreportDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(UreportDemoApplication.class, args);
    }
}
/*
@Configuration
class MyAuditAware implements AuditorAware<String> {
    private static final Optional<String> SYSTEM_ACCOUNT = Optional.of("SYSTEM");

    @Override
    public Optional<String> getCurrentAuditor() {
        return SYSTEM_ACCOUNT;
    }
}*/

