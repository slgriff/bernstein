package bernstein.pipeline;

import java.util.ArrayList;
import java.util.List;

public class Pipeline {
    private List<Phase> phases;

    public Pipeline() {
        phases = new ArrayList<>();
    }

    public Pipeline(List<Phase> phases) {
        phases = new ArrayList<>(phases);
    }

    public boolean addPhase(Phase phase) {
        return phases.add(phase);
    }

    public int execute() {
        for (Phase phase : phases) {
            int retVal = phase.execute();
            if (retVal != 0) {
                return retVal;
            }
        }

        return 0;
    }
}
