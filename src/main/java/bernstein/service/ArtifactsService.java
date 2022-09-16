package bernstein.service;

import bernstein.repository.VersionedArtifactsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ArtifactsService {

    private final VersionedArtifactsRepository versionedArtifactsRepository;

    public ArtifactsService(VersionedArtifactsRepository versionedArtifactsRepository) {
        this.versionedArtifactsRepository = versionedArtifactsRepository;
    }
}
