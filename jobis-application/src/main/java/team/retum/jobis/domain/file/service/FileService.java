package team.retum.jobis.domain.file.service;

import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.domain.file.exception.InvalidExtensionException;
import team.retum.jobis.domain.file.model.FileType;

import java.util.UUID;

import static team.retum.jobis.domain.file.model.FileType.EXTENSION_FILE;
import static team.retum.jobis.domain.file.model.FileType.LOGO_IMAGE;

@Service
public class FileService {

    public String getFullFileName(FileType fileType, String fileName) {
        return fileType.name() + "/" + UUID.randomUUID() + "-" + fileName;
    }

    public void validateExtension(String fileName, FileType fileType) {
        String extension = fileName.substring(fileName.lastIndexOf("."));

        boolean isValid = switch (fileType) {
            case LOGO_IMAGE -> LOGO_IMAGE.validExtensions.contains(extension);
            case EXTENSION_FILE -> EXTENSION_FILE.validExtensions.contains(extension);
        };

        if (!isValid) {
            throw InvalidExtensionException.EXCEPTION;
        }
    }
}
