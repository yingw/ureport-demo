package cn.wilmar.ureport.report;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yin Guo Wei 2018/4/21.
 */
@Controller
class ReportController {
    private final ReportService reportService;

    ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
//    private final ReportProvider fileReportProvider;

//    ReportController(@Qualifier("ureport.fileReportProvider") ReportProvider fileReportProvider) {
//        this.fileReportProvider = fileReportProvider;
//    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/reports";
    }

    @GetMapping("/reports")
    public String getReports(Model model) {
        model.addAttribute("reports", reportService.getReportFiles());
        model.addAttribute("dbReports", reportService.getDbReportFiles());
        model.addAttribute("prefix", "file:");
        model.addAttribute("dbPrefix", "database:");
        return "reports";
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/reports/{id}")
    public void delete(@PathVariable String id) {
        System.out.println("ReportController.delete");
        reportService.deleteReport(id);
    }
}
