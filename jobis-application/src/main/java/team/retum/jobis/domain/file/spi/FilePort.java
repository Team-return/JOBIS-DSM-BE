package team.retum.jobis.domain.file.spi;

import java.io.File;

public interface FilePort {
    void uploadFile(File file, String fileName);
    String getFileUploadUrl(String fullFileName);
}
