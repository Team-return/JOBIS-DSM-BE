package team.retum.jobis.domain.file.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.file.dto.CreateFileUploadUrlRequest;
import team.retum.jobis.domain.file.dto.response.CreateFileUploadUrlResponse;
import team.retum.jobis.domain.file.dto.response.CreateFileUploadUrlResponse.UrlResponse;
import team.retum.jobis.domain.file.service.FileService;
import team.retum.jobis.domain.file.spi.FilePort;

@RequiredArgsConstructor
@UseCase
public class CreateFileUploadUrlUseCase {

    private final FilePort filePort;
    private final FileService fileService;

    public CreateFileUploadUrlResponse execute(CreateFileUploadUrlRequest request) {
        return new CreateFileUploadUrlResponse(
                request.getFiles().stream()
                        .map(
                                file -> {
                                    fileService.validateExtension(file.getFileName(), file.getType());
                                    String fullFileName = fileService.getFullFileName(file.getType(), file.getFileName());
                                    String url = filePort.getFileUploadUrl(fullFileName);
                                    return new UrlResponse(
                                            fullFileName,
                                            url
                                    );
                                }
                        ).toList()
        );
    }
}
