package com.example.jobisapplication.domain.review.dto;

import com.example.jobisapplication.domain.review.model.QnAElement;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CreateReviewRequest {

    private Long companyId;

    private List<QnAElement> qnaElementEntities;

    private Long applicationId;
}
