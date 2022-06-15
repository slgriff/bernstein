package bernstein.pipeline;

import java.util.ArrayList;
import java.util.List;

public class Phase {
    private List<Step> steps;

    public Phase() {
        steps = new ArrayList<>();
    }

    public boolean addStep(Step step) {
        return steps.add(step);
    }

    public int execute() {
        for (Step step : steps) {
            int retVal = step.execute();
            if (retVal != 0) {
                return retVal;
            }
        }

        return 0;
    }
}
