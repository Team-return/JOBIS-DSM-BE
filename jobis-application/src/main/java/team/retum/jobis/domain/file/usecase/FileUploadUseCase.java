package team.retum.jobis.domain.file.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.file.dto.response.FileUploadResponse;
import team.retum.jobis.domain.file.model.FileType;
import team.retum.jobis.domain.file.spi.FilePort;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@UseCase
public class FileUploadUseCase {

    private final FilePort filePort;

    public FileUploadResponse execute(List<File> files, FileType fileType) {
        List<String> fileUrls = files.stream().filter(Objects::nonNull)
                .map(
                        file -> {
                            String fileName = fileType + "/" + UUID.randomUUID() + "-" + file.getName();
                            filePort.uploadFile(file, fileName, fileType);

                            return fileName.replace(" ", "+");
                        }
                ).toList();

        return new FileUploadResponse(fileUrls);
    }
}
