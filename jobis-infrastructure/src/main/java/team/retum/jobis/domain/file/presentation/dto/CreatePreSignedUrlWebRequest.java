package team.retum.jobis.domain.file.presentation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.file.model.FileType;
import team.retum.jobis.global.util.RegexProperty;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreatePreSignedUrlWebRequest {

    @Valid
    private List<FileWebRequest> files;

    @Getter
    @NoArgsConstructor
    public static class FileWebRequest {

        @NotNull
        private FileType type;

        @Pattern(regexp = RegexProperty.FILE_NAME)
        @NotBlank
        private String fileName;
    }
}
