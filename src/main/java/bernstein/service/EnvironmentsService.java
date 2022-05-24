package bernstein.service;

import bernstein.repository.EnvironmentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EnvironmentsService {

    private final EnvironmentsRepository environmentsRepository;

    public EnvironmentsService(EnvironmentsRepository environmentsRepository) {
        this.environmentsRepository = environmentsRepository;
    }
}
