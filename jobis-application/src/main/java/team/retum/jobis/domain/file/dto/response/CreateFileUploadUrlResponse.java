package team.retum.jobis.domain.file.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateFileUploadUrlResponse {
    private final List<UrlResponse> urls;

    @Getter
    @AllArgsConstructor
    public static class UrlResponse {
        private final String filePath;
        private final String preSignedUrl;
    }
}
