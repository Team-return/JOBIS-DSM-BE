package team.retum.jobis.domain.application.model;

import java.util.List;

public enum ApplicationStatus {

    REQUESTED,
    APPROVED,
    SEND,
    FAILED,
    PASS,
    FIELD_TRAIN,
    ACCEPTANCE,
    REJECTED;

    public static final List<ApplicationStatus> DUPLICATE_CHECK = List.of(APPROVED, PASS, FIELD_TRAIN);

    public String getName() {
        return switch (this) {
            case REQUESTED -> "승인 요청";
            case APPROVED -> "승인";
            case SEND -> "기업에게 전송";
            case FAILED -> "탈락";
            case PASS -> "합격";
            case FIELD_TRAIN -> "현장실습";
            case ACCEPTANCE -> "근로계약";
            case REJECTED -> "거부";
        };
    }
}