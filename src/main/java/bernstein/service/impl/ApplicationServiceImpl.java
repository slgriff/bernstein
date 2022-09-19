package bernstein.service.impl;

import bernstein.domain.Environment;
import bernstein.repository.ApplicationRepository;
import bernstein.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<Environment> getEnvironments() {
        return applicationRepository.getEnvironments();
    }
}
