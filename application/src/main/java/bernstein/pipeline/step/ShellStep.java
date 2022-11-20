package bernstein.pipeline.step;

import bernstein.pipeline.Step;

public class ShellStep implements Step {
    private final String command;

    public ShellStep(String command) {
        this.command = command;
    }

    @Override
    public boolean execute() {

        return true;
    }
}
