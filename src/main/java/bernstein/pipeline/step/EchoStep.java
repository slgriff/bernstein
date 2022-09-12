package bernstein.pipeline.step;

import bernstein.pipeline.Step;

public class EchoStep implements Step {
    @Override
    public int execute() {
        System.out.println("Test step execution");
        return 0;
    }
}
