package team.retum.jobis.domain.file.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.common.util.FileUtil;
import team.retum.jobis.domain.file.dto.request.FileRequest;
import team.retum.jobis.domain.file.dto.response.CreateFileUploadUrlResponse;
import team.retum.jobis.domain.file.dto.response.CreateFileUploadUrlResponse.UrlResponse;
import team.retum.jobis.domain.file.spi.FilePort;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreateFileUploadUrlUseCase {

    private final FilePort filePort;

    public CreateFileUploadUrlResponse execute(List<FileRequest> fileRequests) {
        return new CreateFileUploadUrlResponse(
                fileRequests.stream()
                        .map(
                                file -> {
                                    String fullFileName = FileUtil.generateFullFileName(file.type(), file.fileName());
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
