package team.returm.jobis.domain.company.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExistsCompanyRequest {

    @NotBlank(message = "사업자번호는 널 또는 공백을 포함할 수 없습니다.")
    @Size(min = 10, max = 10, message = "사업자 번호는 10글자여야 합니다.")
    private String businessNumber;
}
