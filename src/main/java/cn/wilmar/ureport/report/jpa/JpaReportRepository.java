package cn.wilmar.ureport.report.jpa;

import cn.wilmar.ureport.report.jpa.JpaReport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yinguowei 2017/11/9.
 */
public interface JpaReportRepository extends JpaRepository<JpaReport, String> {
    JpaReport findByFileName(String fileName);
}
