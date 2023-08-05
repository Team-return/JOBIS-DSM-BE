package team.retum.jobis.domain.file.spi;

import team.retum.jobis.domain.file.model.FileType;

import java.io.File;

public interface FilePort {
    void uploadFile(File file, String fileName, FileType fileType);
}
