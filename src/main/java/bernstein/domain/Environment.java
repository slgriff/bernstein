package bernstein.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Environment {
    @NonNull private String name;
}
