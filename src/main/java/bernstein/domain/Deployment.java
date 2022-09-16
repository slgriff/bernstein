package bernstein.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class Deployment {
    private int id;

    @Builder
    public Deployment(int id) {
        this.id = id;
    }
}
