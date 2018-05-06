package cn.wilmar.ureport.report;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Yin Guo Wei 2018/5/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportControllerTest {

    @Autowired WebApplicationContext context;
    @Autowired ReportController reportController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void index() throws Exception {
        assertEquals(reportController.index(), "redirect:/reports");
        mockMvc.perform(get("/")).andExpect(status().isFound());
    }

    @Test
    public void getReports() throws Exception {
        mockMvc.perform(get("/reports")).andExpect(status().isOk());
    }

    @Test
    public void delete() throws Exception {
    }

}