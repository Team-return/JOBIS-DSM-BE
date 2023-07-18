package com.example.jobisapplication.domain.bookmark.domain;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class Bookmark {

    private final Long recruitmentId;

    private final Long studentId;
}
