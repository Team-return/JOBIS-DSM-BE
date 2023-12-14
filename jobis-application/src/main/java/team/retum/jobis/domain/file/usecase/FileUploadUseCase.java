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
                            String fileName = filePort.getFullFileName(fileType, file.getName());
                            filePort.validateExtension(fileName, fileType);
                            filePort.uploadFile(file, fileName);

                            return fileName.replace(" ", "+");
                        }
                ).toList();

        return new FileUploadResponse(fileUrls);
    }


}
