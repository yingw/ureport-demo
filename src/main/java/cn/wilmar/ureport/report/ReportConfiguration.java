package cn.wilmar.ureport.report;

import cn.wilmar.ureport.report.jpa.JpaReportProvider;
import com.bstek.ureport.console.UReportServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yin Guo Wei 2018/4/21.
 */
@Configuration
public class ReportConfiguration {


    @Bean
    public ServletRegistrationBean<UReportServlet> servletRegistrationBean() {
        return new ServletRegistrationBean<>(new UReportServlet(), "/ureport/*");
    }

    @Bean("ureport.jpaReportProvider")
    public JpaReportProvider jpaReportProvider() {
        return new JpaReportProvider();
    }
}
