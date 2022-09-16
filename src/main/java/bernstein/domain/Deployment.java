package bernstein.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Deployment {
    private int id;

    public Deployment(int id) {
        this.id = id;
    }
}
