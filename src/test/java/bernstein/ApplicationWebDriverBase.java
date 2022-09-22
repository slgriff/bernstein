package bernstein;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.BrowserWebDriverContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public abstract class ApplicationWebDriverBase {
    @Value("${local.server.port}")
    private int localServerPort;

    @Value("#{'http://host.testcontainers.internal:' + ${local.server.port} + '/'}")
    private String rootUrl;

    protected BrowserWebDriverContainer<?> browser;

    @Rule
    public BrowserWebDriverContainer<?> browser() {
        return browser;
    }

    @Before
    public void setup() {
        Testcontainers.exposeHostPorts(localServerPort);
    }

    @Test
    public void shouldWork1() {
        System.out.println(rootUrl);
        String url = rootUrl;
        RemoteWebDriver webDriver = browser.getWebDriver();
        webDriver.get(url);
    }
}
