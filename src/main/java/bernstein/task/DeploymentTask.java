package bernstein.task;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Value
@Slf4j
public class DeploymentTask implements Runnable {

    @Override
    public void run() {
        log.info("Test deployment task");
    }
}
