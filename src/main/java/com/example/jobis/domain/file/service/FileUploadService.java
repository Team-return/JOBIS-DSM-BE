package com.example.jobis.domain.file.service;

import com.example.jobis.domain.file.presentation.type.FileType;
import com.example.jobis.infrastructure.s3.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class FileUploadService {
    private final S3Util s3Util;

    public String execute(MultipartFile multipartFile, FileType fileType) {
        return s3Util.uploadImg(multipartFile, fileType);
    }
}
