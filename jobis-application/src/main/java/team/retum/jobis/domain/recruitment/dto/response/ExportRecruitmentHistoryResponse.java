package team.retum.jobis.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExportRecruitmentHistoryResponse {

    private byte[] file;
    private String fileName;
}
