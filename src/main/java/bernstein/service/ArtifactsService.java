package bernstein.service;

import bernstein.repository.ArtifactsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public final class ArtifactsService {

    private final ArtifactsRepository artifactsRepository;

    public ArtifactsService(ArtifactsRepository artifactsRepository) {
        this.artifactsRepository = artifactsRepository;
    }
}
