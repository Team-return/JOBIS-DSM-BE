package team.retum.jobis.domain.file.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreatePreSignedUrlResponse {
    private final List<PreSignedUrlResponse> urls;

    @Getter
    @AllArgsConstructor
    public static class PreSignedUrlResponse {
        private String filePath;
        private String preSignedUrl;
    }
}
