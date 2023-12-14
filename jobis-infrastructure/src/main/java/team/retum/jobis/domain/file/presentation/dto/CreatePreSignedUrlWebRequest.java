package team.retum.jobis.domain.file.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.file.dto.CreateFileUploadUrlRequest;
import team.retum.jobis.domain.file.dto.CreateFileUploadUrlRequest.FileRequest;
import team.retum.jobis.domain.file.model.FileType;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreatePreSignedUrlWebRequest {

    private List<@NotNull FileWebRequest> files;

    @Getter
    @NoArgsConstructor
    public static class FileWebRequest {

        @NotNull
        private FileType type;

        @NotBlank
        private String fileName;
    }

    public CreateFileUploadUrlRequest toDomainRequest() {
        return new CreateFileUploadUrlRequest(
                files.stream()
                        .map(file ->
                                new FileRequest(
                                        file.type,
                                        file.fileName
                                )
                        ).toList()
        );
    }
}
