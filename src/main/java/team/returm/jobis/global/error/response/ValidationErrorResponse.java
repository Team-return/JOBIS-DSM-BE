package team.returm.jobis.global.error.response;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidationErrorResponse {
    private final String status;
    private final Map<String, String> error;
}
