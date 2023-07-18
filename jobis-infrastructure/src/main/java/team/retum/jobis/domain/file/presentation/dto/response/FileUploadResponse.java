package team.retum.jobis.domain.file.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FileUploadResponse {
    private List<String> urls;
}
