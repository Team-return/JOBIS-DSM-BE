package team.returm.jobis.domain.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.returm.jobis.domain.file.presentation.type.FileType;
import team.returm.jobis.infrastructure.s3.S3Util;

@RequiredArgsConstructor
@Service
public class FileUploadService {
    private final S3Util s3Util;

    public String execute(MultipartFile multipartFile, FileType fileType) {
        return s3Util.uploadImg(multipartFile, fileType);
    }
}
