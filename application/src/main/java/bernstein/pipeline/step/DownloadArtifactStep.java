package bernstein.pipeline.step;

import bernstein.pipeline.Step;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DownloadArtifactStep implements Step {
    private final String downloadUrl;

    @Value("${downloadartifactstep.connectiontimeout:3000}")
    private Integer connectionTimeout;

    @Value("${downloadartifactstep.connectiontimeout:5000}")
    private Integer readTimeout;

    public DownloadArtifactStep(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public boolean execute() {
        String fileName = FilenameUtils.getName(downloadUrl);

        try {
            FileUtils.copyURLToFile(new URL(downloadUrl), new File("/tmp", fileName), connectionTimeout, readTimeout);
        } catch (IOException ioException) {
            return false;
        }

        return true;
    }

}
