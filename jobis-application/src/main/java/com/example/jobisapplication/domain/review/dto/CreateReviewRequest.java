package com.example.jobisapplication.domain.review.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateReviewRequest {

    private Long companyId;

    private List<QnAElementEntity> qnaElementEntities;

    private Long applicationId;
}
