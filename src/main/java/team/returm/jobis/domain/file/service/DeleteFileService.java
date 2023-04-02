package team.returm.jobis.domain.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.returm.jobis.infrastructure.s3.S3Util;

@RequiredArgsConstructor
@Service
public class DeleteFileService {
    private final S3Util s3Util;

    public void execute(String path) {
        s3Util.deleteFile(path);
    }
}
