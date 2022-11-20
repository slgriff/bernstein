package bernstein.pipeline;

import java.util.ArrayList;
import java.util.List;

public class Pipeline {
    private final List<Phase> phases;

    public Pipeline() {
        this.phases = new ArrayList<>();
    }

    public Pipeline(List<Phase> phases) {
        this.phases = new ArrayList<>(phases);
    }

    public boolean addPhase(Phase phase) {
        return phases.add(phase);
    }

    public boolean execute() {
        for (Phase phase : phases) {
            if (!phase.execute()) {
                return false;
            }
        }

        return true;
    }
}
