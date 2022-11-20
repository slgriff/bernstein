package bernstein.pipeline;

import java.util.ArrayList;
import java.util.List;

public class Phase {
    private final String name;

    private final List<Step> steps;

    public Phase() {
        this.name = "";
        this.steps = new ArrayList<>();
    }

    public Phase(List<Step> steps) {
        this.name = "";
        this.steps = new ArrayList<>(steps);
    }

    public Phase(String name, List<Step> steps) {
        this.name = name;
        this.steps = new ArrayList<>(steps);
    }

    public boolean addStep(Step step) {
        return steps.add(step);
    }

    public boolean execute() {
        for (Step step : steps) {
            if (!step.execute()) {
                return false;
            }
        }

        return true;
    }

    public String getName() {
        return name;
    }
}
