package bernstein;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class ApplicationFirefoxIT extends ApplicationWebDriverBase {
    public ApplicationFirefoxIT() {
        super();
        browser = new BrowserWebDriverContainer<>().withAccessToHost(true).withCapabilities(new FirefoxOptions());
    }
}
