package team.retum.jobis.domain.file.dto.request;

import team.retum.jobis.domain.file.model.FileType;

public record FileRequest(FileType type, String fileName) {

}
