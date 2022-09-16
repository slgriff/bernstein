package bernstein.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Artifact {
    @NonNull private String name;
}
