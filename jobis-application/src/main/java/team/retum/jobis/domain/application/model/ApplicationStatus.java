package team.retum.jobis.domain.application.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ApplicationStatus {

    REQUESTED("승인 요청"),
    APPROVED("승인"),
    SEND("기업에게 전송"),
    PROCESSING("진행중"),
    FAILED("탈락"),
    PASS("합격"),
    FIELD_TRAIN("현장실습"),
    ACCEPTANCE("근로계약"),
    REJECTED("반려");

    public static final List<ApplicationStatus> DUPLICATE_CHECK = List.of(REQUESTED, SEND, PROCESSING, ACCEPTANCE, APPROVED, PASS, FIELD_TRAIN);
    private final String name;
}
