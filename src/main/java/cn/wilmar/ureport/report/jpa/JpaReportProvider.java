package cn.wilmar.ureport.report.jpa;

import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yinguowei 2017/11/9
 */
public class JpaReportProvider implements ReportProvider {
    private static final String PREFIX = "database:";
    private static Logger logger = LoggerFactory.getLogger(JpaReportProvider.class);
    @Autowired
    private JpaReportRepository reportRepository;

    @Override
    public InputStream loadReport(String file) {
        logger.debug("JpaReportProvider.loadReport, file = {}", file);
        if (file.startsWith(PREFIX)) {
            file = file.substring(PREFIX.length(), file.length());
        }
        // TODO		file=ReportUtils.decodeFileName(file);
        // TODO get File name 带上我们自定义参数，找不到，临时解决方案：.ureport.xml后面全部截掉
        JpaReport report = reportRepository.findByFileName(file);
        if (report == null) {
            logger.error("Can't find report: {}", file);
            throw new RuntimeException("Can't find report: " + file);
        }
        String fileStr = report.getFileContent();
        try {
            return new ByteArrayInputStream(
                    fileStr
                            .getBytes(StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void deleteReport(String s) {
        // TODO cache exists
        if (s.startsWith(PREFIX)) {
            s = s.substring(9);
        }
        reportRepository.deleteById(s);
    }

    @Override
    public List<ReportFile> getReportFiles() {
        logger.debug("JpaReportProvider.getReportFiles");
        List<JpaReport> reports = reportRepository.findAll();
        // 数据库的 LastModifiedDate 应该是非空的，但是不能在 Entity 内设置 NotNull。建议不管。
        return reports.stream()
                .map(report -> {
                    final Instant lastModifiedDate = report.getLastModifiedDate() == null ? ZonedDateTime.now().toInstant() : report.getLastModifiedDate();
                    return new ReportFile(
                            report.getFileName(),
                            Date.from(lastModifiedDate));
                })
                .collect(Collectors.toList());
    }

    @Override
    public void saveReport(String fileName, String content) {
        logger.debug("JpaReportProvider.saveReport, fileName: {}", fileName);
        if (fileName.startsWith(PREFIX)) {
            fileName = fileName.substring(PREFIX.length());
        }
        JpaReport report = reportRepository.findByFileName(fileName);
        if (report == null) {
            logger.debug("Haven't found report: {}, create NEW!", fileName);
            report = new JpaReport(fileName);
        } else {
            logger.debug("Found report: {}, update!", fileName);
        }
        report.setFileContent(content);
        reportRepository.save(report);
    }

    @Override
    public String getName() {
        return "数据库存档报表";
    }

    @Override
    public boolean disabled() {
        return false;
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

}
