package team.retum.jobis.domain.file.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.file.dto.CreatePreSignedUrlRequest;
import team.retum.jobis.domain.file.dto.response.CreatePreSignedUrlResponse;
import team.retum.jobis.domain.file.dto.response.CreatePreSignedUrlResponse.PreSignedUrlResponse;
import team.retum.jobis.domain.file.spi.FilePort;

@RequiredArgsConstructor
@UseCase
public class CreatePreSignedUrlUseCase {

    private final FilePort filePort;

    public CreatePreSignedUrlResponse execute(CreatePreSignedUrlRequest request) {
        return new CreatePreSignedUrlResponse(
                request.getFiles().stream()
                        .map(
                                file -> {
                                    String fileName = filePort.getFullFileName(file.getType(), file.getFileName());
                                    String url = filePort.getPreSignedUrl(fileName);
                                    return new PreSignedUrlResponse(
                                            fileName,
                                            url
                                    );
                                }
                        ).toList()
        );
    }
}
