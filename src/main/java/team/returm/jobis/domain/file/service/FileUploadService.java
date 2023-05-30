package team.returm.jobis.domain.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.returm.jobis.domain.file.exception.FileNotFoundException;
import team.returm.jobis.domain.file.presentation.dto.response.FileUploadResponse;
import team.returm.jobis.domain.file.presentation.type.FileType;
import team.returm.jobis.infrastructure.s3.S3Util;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileUploadService {
    private final S3Util s3Util;

    public FileUploadResponse execute(List<MultipartFile> multipartFiles, FileType fileType) {
        List<String> fileUrls = multipartFiles.stream()
                .map(
                        multipartFile -> {
                            if(multipartFile == null || multipartFile.getOriginalFilename() == null)
                                throw FileNotFoundException.EXCEPTION;
                            s3Util.validateExtension(multipartFile.getOriginalFilename(), fileType);
                            String fileName = fileType + "/" + UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
                            s3Util.upload(multipartFile, fileName);

                            return fileName;
                        }
                ).toList();

        return new FileUploadResponse(fileUrls);
    }
}
