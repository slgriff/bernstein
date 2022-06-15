package bernstein.task;

import bernstein.pipeline.Pipeline;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeploymentTask implements Runnable {

    private Pipeline pipeline;

    public DeploymentTask(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public void run() {
        log.info("Test deployment task");
        pipeline.execute();
    }
}
