package team.retum.jobis.domain.file.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.file.dto.CreatePreSignedUrlRequest;
import team.retum.jobis.domain.file.dto.CreatePreSignedUrlRequest.FileRequest;
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

    public CreatePreSignedUrlRequest toDomainRequest() {
        return new CreatePreSignedUrlRequest(
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
