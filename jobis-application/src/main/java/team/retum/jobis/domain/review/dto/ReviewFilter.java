package team.retum.jobis.domain.review.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.review.model.InterviewLocation;
import team.retum.jobis.domain.review.model.InterviewType;

@Getter
@Builder
public class ReviewFilter {

    private final Integer page;

    private final InterviewType type;

    private final InterviewLocation location;

    private final Long companyId;

    private final Integer year;

    private final Long code;

    @Builder.Default
    private int limit = 10;

    public int getOffset() {
        return (page - 1) * limit;
    }
}
