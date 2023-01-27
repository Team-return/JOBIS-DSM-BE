package com.example.jobis.domain.file.service;

import com.example.jobis.infrastructure.s3.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteFileService {
    private final S3Util s3Util;

    public void execute(String path) {
        s3Util.deleteFile(path);
    }
}
