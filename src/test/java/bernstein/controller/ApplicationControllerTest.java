package bernstein.controller;

import bernstein.service.ApplicationService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @WithMockUser
    public void shouldWork2() throws Exception {
        HtmlPage page = webClient.getPage("/deployments");
        assertThat(page.getTitleText()).isEqualTo("Deployments");
    }
}
