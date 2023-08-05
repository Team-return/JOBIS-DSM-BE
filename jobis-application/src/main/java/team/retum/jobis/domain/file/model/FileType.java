package team.retum.jobis.domain.file.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum FileType {

    LOGO_IMAGE(List.of(".jpg", ".png", ".svg")),
    EXTENSION_FILE(List.of(".pdf", ".ppt", ".pptx", ".hwp", ".jpg", ".png", ".zip", ".txt", ".mp4"));

    public final List<String> validExtensions;
}

