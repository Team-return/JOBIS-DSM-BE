package com.example.jobis.domain.file.presentation;

import com.example.jobis.domain.file.service.FileUploadService;
import com.example.jobis.domain.file.presentation.type.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileUploadService fileUploadService;

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile,
                             @RequestParam("type")FileType fileType) {
        return fileUploadService.execute(multipartFile, fileType);
    }
}
