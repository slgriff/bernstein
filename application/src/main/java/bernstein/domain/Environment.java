package bernstein.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class Environment {
    @NonNull
    private String name;

    @Builder
    public Environment(@NonNull String name) {
        this.name = name;
    }
}
