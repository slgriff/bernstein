package bernstein.pipeline.step;

import bernstein.pipeline.Step;

public class NoopStep implements Step {
    @Override
    public boolean execute() {
        return true;
    }
}
