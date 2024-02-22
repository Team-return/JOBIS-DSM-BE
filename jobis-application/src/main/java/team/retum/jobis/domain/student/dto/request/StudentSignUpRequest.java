package team.retum.jobis.domain.student.dto.request;

import lombok.Builder;
import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.student.model.Gender;

@Builder
public record StudentSignUpRequest(
        String email,
        String password,
        Integer grade,
        String name,
        Integer classRoom,
        Integer number,
        Gender gender,
        String profileImageUrl,
        PlatformType platformType
) {}
