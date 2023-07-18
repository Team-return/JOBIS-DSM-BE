package team.retum.jobis.domain.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.retum.jobis.thirdparty.s3.S3Adapter;

@RequiredArgsConstructor
@Service
public class DeleteFileService {
    private final S3Adapter s3Adapter;

    public void execute(String path) {
        s3Adapter.deleteFile(path);
    }
}
