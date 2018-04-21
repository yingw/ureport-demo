package cn.wilmar.ureport.report;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Yin Guo Wei 2018/4/21.
 */
@Component
class ReportDataLoader implements CommandLineRunner {
    private final ReportService reportService;

    ReportDataLoader(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public void run(String... args) throws Exception {
        reportService.createSampleReport();
    }
}
