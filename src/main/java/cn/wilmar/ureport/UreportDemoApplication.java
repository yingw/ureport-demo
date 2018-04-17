package cn.wilmar.ureport;

import com.bstek.ureport.console.UReportServlet;
import com.bstek.ureport.provider.report.ReportProvider;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

enum Gender {
    /**
     * Male
     */
    MALE,
    /**
     * Female
     */
    FEMALE;

    private static final List<Gender> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Gender randomGender() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

@Repository
interface UserRepository extends JpaRepository<User, Long> {

}

/**
 * @author yinguowei
 */
@ImportResource("classpath:context.xml")
@SpringBootApplication
public class UreportDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(UreportDemoApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean<UReportServlet> servletRegistrationBean() {
        // ServletName默认值为首字母小写，即myServlet
        return new ServletRegistrationBean<>(new UReportServlet(), "/ureport/*");
    }
}

@Controller
class IndexController {
    @RequestMapping("/")
    public String index() {
        return "redirect:/reports";
    }
}

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @NonNull String username;
    @Enumerated(EnumType.STRING) Gender gender = Gender.randomGender();
    @NonNull String email;
}

@Component
class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ReportService reportService;

    DataLoader(UserRepository userRepository, ReportService reportService) {
        this.userRepository = userRepository;
        this.reportService = reportService;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Jojo", "Jon", "James", "Jack", "Jacky", "Jean", "Justin", "Judy", "Julia", "Jason", "Justin", "Jane", "Joseph", "Jerry", "Jenny")
                .forEach(name -> userRepository.save(new User(name, name.toLowerCase() + "@sample.com")));
        userRepository.findAll().forEach(System.out::println);
        reportService.createSampleReport();
    }
}

@Service
class ReportService {
    private final ReportProvider reportProvider;

    ReportService(@Qualifier("ureport.fileReportProvider") ReportProvider reportProvider) {
        this.reportProvider = reportProvider;
    }

    void createSampleReport() {
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ureport><cell expand=\"None\" name=\"A1\" row=\"1\" col=\"1\" col-span=\"4\"><cell-style font-size=\"9\" forecolor=\"0,0,0\" font-family=\"宋体\" align=\"center\" valign=\"middle\"></cell-style><chart-value><dataset dataset-name=\"users\" type=\"pie\" category-property=\"GENDER\" series-type=\"text\" series-text=\"count\" value-property=\"ID\" collect-type=\"count\"/></chart-value></cell><cell expand=\"None\" name=\"A2\" row=\"2\" col=\"1\"><cell-style font-size=\"9\" forecolor=\"0,0,0\" font-family=\"宋体\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><cell expand=\"None\" name=\"B2\" row=\"2\" col=\"2\"><cell-style font-size=\"9\" forecolor=\"0,0,0\" font-family=\"宋体\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><cell expand=\"None\" name=\"C2\" row=\"2\" col=\"3\"><cell-style font-size=\"9\" forecolor=\"0,0,0\" font-family=\"宋体\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><cell expand=\"None\" name=\"D2\" row=\"2\" col=\"4\"><cell-style font-size=\"9\" forecolor=\"0,0,0\" font-family=\"宋体\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><cell expand=\"None\" name=\"A3\" row=\"3\" col=\"1\"><cell-style font-size=\"10\" forecolor=\"255,255,255\" bgcolor=\"51,122,183\" bold=\"true\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><simple-value><![CDATA[ID]]></simple-value></cell><cell expand=\"None\" name=\"B3\" row=\"3\" col=\"2\"><cell-style font-size=\"10\" forecolor=\"255,255,255\" bgcolor=\"51,122,183\" bold=\"true\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><simple-value><![CDATA[Useranme]]></simple-value></cell><cell expand=\"None\" name=\"C3\" row=\"3\" col=\"3\"><cell-style font-size=\"10\" forecolor=\"255,255,255\" bgcolor=\"51,122,183\" bold=\"true\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><simple-value><![CDATA[Gender]]></simple-value></cell><cell expand=\"None\" name=\"D3\" row=\"3\" col=\"4\"><cell-style font-size=\"10\" forecolor=\"255,255,255\" bgcolor=\"51,122,183\" bold=\"true\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><simple-value><![CDATA[Email]]></simple-value></cell><cell expand=\"Down\" name=\"A4\" row=\"4\" col=\"1\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><dataset-value dataset-name=\"users\" aggregate=\"select\" property=\"ID\" order=\"none\" mapping-type=\"simple\"></dataset-value></cell><cell expand=\"Down\" name=\"B4\" row=\"4\" col=\"2\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><dataset-value dataset-name=\"users\" aggregate=\"select\" property=\"USERNAME\" order=\"none\" mapping-type=\"simple\"></dataset-value></cell><cell expand=\"Down\" name=\"C4\" row=\"4\" col=\"3\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><dataset-value dataset-name=\"users\" aggregate=\"select\" property=\"GENDER\" order=\"none\" mapping-type=\"simple\"></dataset-value></cell><cell expand=\"Down\" name=\"D4\" row=\"4\" col=\"4\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"><left-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><right-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><top-border width=\"1\" style=\"solid\" color=\"0,0,0\"/><bottom-border width=\"1\" style=\"solid\" color=\"0,0,0\"/></cell-style><dataset-value dataset-name=\"users\" aggregate=\"select\" property=\"EMAIL\" order=\"none\" mapping-type=\"simple\"></dataset-value></cell><cell expand=\"None\" name=\"A5\" row=\"5\" col=\"1\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><cell expand=\"None\" name=\"B5\" row=\"5\" col=\"2\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><cell expand=\"None\" name=\"C5\" row=\"5\" col=\"3\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><cell expand=\"None\" name=\"D5\" row=\"5\" col=\"4\"><cell-style font-size=\"10\" align=\"center\" valign=\"middle\"></cell-style><simple-value><![CDATA[]]></simple-value></cell><row row-number=\"1\" height=\"242\"/><row row-number=\"2\" height=\"19\"/><row row-number=\"3\" height=\"18\"/><row row-number=\"4\" height=\"18\"/><row row-number=\"5\" height=\"18\"/><column col-number=\"1\" width=\"80\"/><column col-number=\"2\" width=\"80\"/><column col-number=\"3\" width=\"80\"/><column col-number=\"4\" width=\"80\"/><datasource name=\"h2database\" type=\"jdbc\" username=\"sa\" password=\"\" url=\"jdbc:h2:mem:testdb\" driver=\"org.h2.Driver\"><dataset name=\"users\" type=\"sql\"><sql><![CDATA[select * from USER]]></sql><field name=\"ID\"/><field name=\"EMAIL\"/><field name=\"GENDER\"/><field name=\"USERNAME\"/></dataset></datasource><paper type=\"A4\" left-margin=\"90\" right-margin=\"90\"\n" +
                "    top-margin=\"72\" bottom-margin=\"72\" paging-mode=\"fitpage\" fixrows=\"0\"\n" +
                "    width=\"595\" height=\"842\" orientation=\"portrait\" html-report-align=\"left\" bg-image=\"\" html-interval-refresh-value=\"0\" column-enabled=\"false\"></paper></ureport>";
        String file = "sample.ureport.xml";
        reportProvider.saveReport(file, xmlContent);
        System.out.println("ReportService.createSampleReport");
    }
}

@Controller
class ReportController {


    private final ReportProvider reportProvider;

    ReportController(@Qualifier("ureport.fileReportProvider") ReportProvider reportProvider) {
        this.reportProvider = reportProvider;
    }

    @GetMapping("/reports")
    public String getReports(Model model) {
        model.addAttribute("reports", reportProvider.getReportFiles());
        model.addAttribute("prefix", reportProvider.getPrefix());
        return "reports";
    }

//    @ResponseBody
//    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/reports/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        System.out.println("ReportController.delete");
        reportProvider.deleteReport(id);
        return ResponseEntity.ok().build();
//        return "{}";
//        return new HashMap<>();
    }
}