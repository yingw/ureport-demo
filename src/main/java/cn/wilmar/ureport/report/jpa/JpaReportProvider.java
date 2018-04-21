package cn.wilmar.ureport.report.jpa;

import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Clob;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建 by 殷国伟 于 2017/11/9.
 *
 * @author yingu
 */
//@Configuration
public class JpaReportProvider implements ReportProvider {
    private static final String PREFIX = "database:";
    private static final String SUFFIX = ".ureport.xml";
    private static Logger logger = LoggerFactory.getLogger(JpaReportProvider.class);
    @Autowired
    JpaReportRepository reportRepository;

    @Override
    public InputStream loadReport(String file) {
        if (file.startsWith(PREFIX)) {
            file = file.substring(PREFIX.length(), file.length());
        }
        logger.info("加载报表：" + file);
        // BUG, TODO get File name 带上我们自定义参数，找不到，临时解决方案：.ureport.xml后面全部截掉
//        file = file.substring(0, file.indexOf(".ureport.xml") + 12);
//        System.out.println("file = " + file);
//        Clob fileContent = reportRepository.findByFileName(file).getFileContent();
//        String fileStr = clobToString(fileContent);
        JpaReport report = reportRepository.findByFileName(file);
        if (report == null) {
            throw new RuntimeException("找不到报表：" + file);
        }
        String fileStr = report.getFileContent();
        logger.info("成功加载报表内容：\n" + fileStr);
        try {
            return new ByteArrayInputStream(
                    fileStr
                            .getBytes(StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteReport(String s) {
//        logger.error("不支持在设计器中删除数据库报表，请到管理控制台删除！");
//        throw new RuntimeException("不支持在设计器中删除数据库报表，请到管理控制台删除！");
        if (s.startsWith("database:")) {
            s = s.substring(9);
        }
        reportRepository.deleteById(s);
    }

    @Override
    public List<ReportFile> getReportFiles() {
        logger.info("加载所有报表……");
        List<JpaReport> reports = reportRepository.findAll();
        logger.info("共加载到 {} 张报表", reports.size());
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


    private String transferFileNameToReportName(String fileName) {
        if (fileName.startsWith(PREFIX)) {
            fileName = fileName.substring(PREFIX.length());
        }
        if (fileName.toLowerCase().endsWith(SUFFIX)) {
            fileName = fileName.substring(0, fileName.length() - SUFFIX.length());
        }
        return fileName;
    }

    @Override
    public void saveReport(String fileName, String content) {
        logger.info("保存报表：{}，内容：\n{}", fileName, content);
        if (fileName.startsWith(PREFIX)) {
            fileName = fileName.substring(PREFIX.length());
        }
        JpaReport report = reportRepository.findByFileName(fileName);
        if (report == null) {
            logger.info("没有找到报表：{}，新建报表！", fileName);
            report = new JpaReport(fileName);
            // 初始化必填项 TODO
//            report.setActivated(true);
//            report.setName(transferFileNameToReportName(fileName));
        } else {
            logger.info("找到报表：{}，更新报表！", fileName);
        }
//        report.setName(fileName);

//        ByteArrayInputStream stream = new ByteArrayInputStream(content.getBytes());

//        try {
//            Clob clob = new SerialClob(content.toCharArray());
//            report.setFileContent(clob);
        report.setFileContent(content);
        reportRepository.save(report);
        logger.info("保存成功！");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
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
/*

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
*/

    /**
     * Clob类型转换成String类型
     */
    public String clobToString(final Clob clob) {

        if (clob == null) {
            return null;
        }

        Reader is = null;
        try {
            is = clob.getCharacterStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(is);

        String str = null;
        try {
            str = br.readLine();    // 读取第一行
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuffer sb = new StringBuffer();
        while (str != null) {    // 如果没有到达流的末尾，则继续读取下一行
            sb.append(str);
            try {
                str = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String returnString = sb.toString();

        return returnString;
        // 其他写法
        // System.out.println("fileContent.getSubString(0, (int) fileContent.length()).toString() = " + fileContent.getSubString(1, (int) fileContent.length()));

    }
}
