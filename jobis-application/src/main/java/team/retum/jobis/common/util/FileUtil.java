package team.retum.jobis.common.util;

import team.retum.jobis.domain.file.exception.InvalidExtensionException;
import team.retum.jobis.domain.file.model.FileType;

import java.util.UUID;

import static team.retum.jobis.domain.file.model.FileType.EXTENSION_FILE;
import static team.retum.jobis.domain.file.model.FileType.LOGO_IMAGE;

public class FileUtil {

    public static String generateFullFileName(FileType fileType, String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf("."));

        boolean isValid = switch (fileType) {
            case LOGO_IMAGE -> LOGO_IMAGE.validExtensions.contains(extension);
            case EXTENSION_FILE -> EXTENSION_FILE.validExtensions.contains(extension);
        };

        if (!isValid) {
            throw InvalidExtensionException.EXCEPTION;
        }

        return fileType.name() + "/" + UUID.randomUUID() + "-" + fileName;
    }
}
