package team.retum.jobis.domain.file.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.file.model.FileType;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreatePreSignedUrlRequest {

    private final List<FileRequest> files;

    @Getter
    @AllArgsConstructor
    public static class FileRequest {
        private final FileType type;
        private final String fileName;
    }
}
