package cn.wilmar.ureport.report.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yinguowei 2017/11/9.
 */
public interface JpaReportRepository extends JpaRepository<JpaReport, String> {
    /**
     * findByFileName
     * @param fileName name
     * @return JpaReport
     */
    JpaReport findByFileName(String fileName);
}
