package cn.wilmar.ureport.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yin Guo Wei 2018/4/21.
 */
@Controller
public class ReportController {
    private final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final ReportService reportService;

    ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/reports";
    }

    @GetMapping("/reports")
    public String getReports(Model model) {
        logger.debug("ReportController.getReports");
        model.addAttribute("reports", reportService.getReportFiles());
        model.addAttribute("dbReports", reportService.getDbReportFiles());
        model.addAttribute("prefix", "file:");
        model.addAttribute("dbPrefix", "database:");
        return "reports";
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/reports/{id}")
    public void delete(@PathVariable String id) {
        logger.debug("ReportController.delete {}", id);
        reportService.deleteReport(id);
    }
}
