package bernstein.pipeline.step;

import bernstein.pipeline.Step;

public class EchoStep implements Step {
    @Override
    public boolean execute() {
        return true;
    }
}
