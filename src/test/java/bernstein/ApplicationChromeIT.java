package bernstein;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class ApplicationChromeIT extends ApplicationWebDriverBase {
    public ApplicationChromeIT() {
        super();
        browser = new BrowserWebDriverContainer<>().withAccessToHost(true).withCapabilities(new ChromeOptions());
    }
}
