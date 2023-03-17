package team.returm.jobis.domain.file.service;

import team.returm.jobis.infrastructure.s3.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteFileService {
    private final S3Util s3Util;

    public void execute(String path) {
        s3Util.deleteFile(path);
    }
}