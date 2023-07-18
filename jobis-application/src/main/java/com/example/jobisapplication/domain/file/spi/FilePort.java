package com.example.jobisapplication.domain.file.spi;

import com.example.jobisapplication.domain.file.model.FileType;

import java.io.File;

public interface FilePort {
    void uploadFile(File file, String fileName, FileType fileType);
}
