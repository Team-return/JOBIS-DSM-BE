package team.retum.jobis.domain.file.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.file.dto.request.FileRequest;
import team.retum.jobis.domain.file.dto.response.CreateFileUploadUrlResponse;
import team.retum.jobis.domain.file.presentation.dto.CreatePreSignedUrlWebRequest;
import team.retum.jobis.domain.file.usecase.CreateFileUploadUrlUseCase;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileWebAdapter {
    private final CreateFileUploadUrlUseCase createFileUploadUrlUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/pre-signed")
    public CreateFileUploadUrlResponse createPreSignedUrl(
            @RequestBody @Valid CreatePreSignedUrlWebRequest request
    ) {
        return createFileUploadUrlUseCase.execute(
                request.getFiles().stream()
                        .map(file -> new FileRequest(file.getType(), file.getFileName()))
                        .toList()
        );
    }
}
