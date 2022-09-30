package bernstein.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class Artifact {
    @NonNull
    private String name;

    @Builder
    public Artifact(@NonNull String name) {
        this.name = name;
    }
}
