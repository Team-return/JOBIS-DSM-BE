package team.retum.jobis.domain.file.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.file.dto.response.FileUploadResponse;
import team.retum.jobis.domain.file.model.FileType;
import team.retum.jobis.domain.file.service.FileService;
import team.retum.jobis.domain.file.spi.FilePort;

import java.io.File;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@UseCase
public class FileUploadUseCase {

    private final FilePort filePort;
    private final FileService fileService;

    public FileUploadResponse execute(List<File> files, FileType fileType) {
        List<String> fileUrls = files.stream().filter(Objects::nonNull)
                .map(
                        file -> {
                            String fileName = fileService.getFullFileName(fileType, file.getName());
                            fileService.validateExtension(fileName, fileType);
                            filePort.uploadFile(file, fileName);

                            return fileName.replace(" ", "+");
                        }
                ).toList();

        return new FileUploadResponse(fileUrls);
    }


}
