package bernstein.service;

import bernstein.domain.Environment;
import java.util.List;

public interface ApplicationService {
    List<Environment> getEnvironments();
}
