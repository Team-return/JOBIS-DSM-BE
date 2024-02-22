package team.retum.jobis.domain.review.dto;

public record QnAElement(
        String question,
        String answer,
        Long codeId
) {}
