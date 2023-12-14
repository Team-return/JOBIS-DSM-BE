package team.retum.jobis.domain.file.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.common.util.FileUtil;
import team.retum.jobis.domain.file.dto.CreateFileUploadUrlRequest;
import team.retum.jobis.domain.file.dto.response.CreateFileUploadUrlResponse;
import team.retum.jobis.domain.file.dto.response.CreateFileUploadUrlResponse.UrlResponse;
import team.retum.jobis.domain.file.spi.FilePort;

@RequiredArgsConstructor
@Service
public class CreateFileUploadUrlUseCase {

    private final FilePort filePort;

    public CreateFileUploadUrlResponse execute(CreateFileUploadUrlRequest request) {
        return new CreateFileUploadUrlResponse(
                request.getFiles().stream()
                        .map(
                                file -> {
                                    String fullFileName = FileUtil.generateFullFileName(file.getType(), file.getFileName());
                                    String url = filePort.generateFileUploadUrl(fullFileName);
                                    return new UrlResponse(
                                            fullFileName,
                                            url
                                    );
                                }
                        ).toList()
        );
    }
}
