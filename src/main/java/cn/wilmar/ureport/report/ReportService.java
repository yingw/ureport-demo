package cn.wilmar.ureport.report;

import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.file.FileReportProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import cn.wilmar.ureport.report.jpa.JpaReportProvider;

import java.util.List;

/**
 * @author Yin Guo Wei 2018/4/21.
 */
@Service
public class ReportService {

    private final FileReportProvider fileReportProvider;
    private final JpaReportProvider jpaReportProvider;
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driverClass;

    //    @Qualifier("ureport.fileReportProvider") ReportProvider fileReportProvider
    ReportService(FileReportProvider fileReportProvider, JpaReportProvider jpaReportProvider) {
        this.fileReportProvider = fileReportProvider;
        this.jpaReportProvider = jpaReportProvider;
    }

    public void createSampleReport() {
        /* copied from manually created report: Users' gender statistics */
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ureport><cell expand=\"None\" name=\"A1\" row=\"1\" col=\"1\" col-span=\"4\"><cell-style font-size=\"9\" forecolor=\"0,0,0\" font-family=\"宋体\" align=\"center\" valign=\"middle\"></cell-style><chart-value><dataset dataset-name=\"users\" type=\"pie\" category-property=\"GENDER\" series-type=\"text\" series-text=\"count\" value-property=\"ID\" collect-type=\"count\"/></chart-value></cell><cell expand=\"None\" name=\"A2\" row=\"2\" col=\"1\"><cell-style font-size=\"9\" forecolor=\"0,0,0\" font-family=\"宋体\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><cell expand=\"None\" name=\"B2\" row=\"2\" col=\"2\"><cell-style font-size=\"9\" forecolor=\"0,0,0\" font-family=\"宋体\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><cell expand=\"None\" name=\"C2\" row=\"2\" col=\"3\"><cell-style font-size=\"9\" forecolor=\"0,0,0\" font-family=\"宋体\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><cell expand=\"None\" name=\"D2\" row=\"2\" col=\"4\"><cell-style font-size=\"9\" forecolor=\"0,0,0\" font-family=\"宋体\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><cell expand=\"None\" name=\"A3\" row=\"3\" col=\"1\"><cell-style font-size=\"10\" forecolor=\"255,255,255\" bgcolor=\"51,122,183\" bold=\"true\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><simple-value><![CDATA[ID]]></simple-value></cell><cell expand=\"None\" name=\"B3\" row=\"3\" col=\"2\"><cell-style font-size=\"10\" forecolor=\"255,255,255\" bgcolor=\"51,122,183\" bold=\"true\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><simple-value><![CDATA[Useranme]]></simple-value></cell><cell expand=\"None\" name=\"C3\" row=\"3\" col=\"3\"><cell-style font-size=\"10\" forecolor=\"255,255,255\" bgcolor=\"51,122,183\" bold=\"true\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><simple-value><![CDATA[Gender]]></simple-value></cell><cell expand=\"None\" name=\"D3\" row=\"3\" col=\"4\"><cell-style font-size=\"10\" forecolor=\"255,255,255\" bgcolor=\"51,122,183\" bold=\"true\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><simple-value><![CDATA[Email]]></simple-value></cell><cell expand=\"Down\" name=\"A4\" row=\"4\" col=\"1\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><dataset-value dataset-name=\"users\" aggregate=\"select\" property=\"ID\" order=\"none\" mapping-type=\"simple\"></dataset-value></cell><cell expand=\"Down\" name=\"B4\" row=\"4\" col=\"2\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><dataset-value dataset-name=\"users\" aggregate=\"select\" property=\"USERNAME\" order=\"none\" mapping-type=\"simple\"></dataset-value></cell><cell expand=\"Down\" name=\"C4\" row=\"4\" col=\"3\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><dataset-value dataset-name=\"users\" aggregate=\"select\" property=\"GENDER\" order=\"none\" mapping-type=\"simple\"></dataset-value></cell><cell expand=\"Down\" name=\"D4\" row=\"4\" col=\"4\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><dataset-value dataset-name=\"users\" aggregate=\"select\" property=\"EMAIL\" order=\"none\" mapping-type=\"simple\"></dataset-value></cell><cell expand=\"None\" name=\"A5\" row=\"5\" col=\"1\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><cell expand=\"None\" name=\"B5\" row=\"5\" col=\"2\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><cell expand=\"None\" name=\"C5\" row=\"5\" col=\"3\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><cell expand=\"None\" name=\"D5\" row=\"5\" col=\"4\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><row row-number=\"1\" height=\"242\"/><row row-number=\"2\" height=\"19\"/><row row-number=\"3\" height=\"18\"/><row row-number=\"4\" height=\"18\"/><row row-number=\"5\" height=\"18\"/><column col-number=\"1\" width=\"80\"/><column col-number=\"2\" width=\"80\"/><column col-number=\"3\" width=\"80\"/><column col-number=\"4\" width=\"80\"/>" +
                "<datasource name=\"sampleDataSource\" type=\"jdbc\" username=\"" + username + "\" password=\"" + password + "\" url=\"" + url + "\" driver=\"" + driverClass + "\">" +
//                "<datasource name=\"sampleDataSource\" type=\"jdbc\" username=\"sa\" password=\"\" url=\"jdbc:h2:mem:testdb\" driver=\"org.h2.Driver\">" +
                "<dataset name=\"users\" type=\"sql\"><sql><![CDATA[select * from USER]]></sql><field name=\"ID\"/><field name=\"EMAIL\"/><field name=\"GENDER\"/><field name=\"USERNAME\"/></dataset></datasource><paper type=\"A4\" left-margin=\"90\" right-margin=\"90\"\n" +
                "    top-margin=\"72\" bottom-margin=\"72\" paging-mode=\"fitpage\" fixrows=\"0\"\n" +
                "    width=\"595\" height=\"842\" orientation=\"portrait\" html-report-align=\"left\" bg-image=\"\" html-interval-refresh-value=\"0\" column-enabled=\"false\"></paper></ureport>";
        String file = "sample.ureport.xml";
        fileReportProvider.saveReport(file, xmlContent);

        jpaReportProvider.saveReport(file, xmlContent);

        System.out.println("ReportService.createSampleReport");
    }


    List<ReportFile> getReportFiles() {
        return fileReportProvider.getReportFiles();
    }

    List<ReportFile> getDbReportFiles() {
        return jpaReportProvider.getReportFiles();
    }

    void deleteReport(String file) {
        if (file.startsWith("file:")) {
            fileReportProvider.deleteReport(file);
        } else if (file.startsWith("database:")) {
            jpaReportProvider.deleteReport(file);
        } else {
            System.err.println("ReportService.deleteReport, error when delete: " + file);
        }
    }
}
