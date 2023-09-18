package team.retum.jobis.domain.application.model;

import java.util.List;

public enum ApplicationStatus {

    REQUESTED, // 승인 요청
    APPROVED, // 승인
    FAILED, // 탈락
    PASS, // 통과
    FIELD_TRAIN, // 현장실습,
    REJECTED; // 거부

    public static final List<ApplicationStatus> DUPLICATE_CHECK = List.of(APPROVED, PASS, FIELD_TRAIN);
}