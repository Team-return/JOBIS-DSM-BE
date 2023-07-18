package com.example.jobisapplication.domain.review.dto;

import com.example.jobisapplication.domain.review.model.QnAElement;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateReviewRequest {

    private Long companyId;

    private List<QnAElement> qnaElementEntities;

    private Long applicationId;
}
