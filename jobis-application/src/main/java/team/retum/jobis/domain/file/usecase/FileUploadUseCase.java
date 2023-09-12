package team.retum.jobis.domain.file.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.file.dto.response.FileUploadResponse;
import team.retum.jobis.domain.file.exception.InvalidExtensionException;
import team.retum.jobis.domain.file.model.FileType;
import team.retum.jobis.domain.file.spi.FilePort;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static team.retum.jobis.domain.file.model.FileType.EXTENSION_FILE;
import static team.retum.jobis.domain.file.model.FileType.LOGO_IMAGE;

@RequiredArgsConstructor
@UseCase
public class FileUploadUseCase {

    private final FilePort filePort;

    public FileUploadResponse execute(List<File> files, FileType fileType) {
        List<String> fileUrls = files.stream().filter(Objects::nonNull)
                .map(
                        file -> {
                            String fileName = fileType + "/" + UUID.randomUUID() + "-" + file.getName();
                            validateExtension(fileName, fileType);
                            filePort.uploadFile(file, fileName, fileType);

                            return fileName.replace(" ", "+");
                        }
                ).toList();

        return new FileUploadResponse(fileUrls);
    }

    private void validateExtension(String fileName, FileType fileType) {
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
