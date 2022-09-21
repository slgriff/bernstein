package bernstein.controller;

import bernstein.service.ApplicationService;
import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(ApplicationController.class)
public class ApplicationControllerTest {

    @Autowired
    private WebClient webClient;

    @MockBean
    private ApplicationService applicationService;

    @Test
    public void shouldWork1() throws Exception {
        webClient.getPage("/");
    }
}
