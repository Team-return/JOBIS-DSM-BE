package com.example.jobisapplication.domain.bookmark.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StudentBookmarksVO {
    private final String companyName;
    private final Long recruitmentId;
    private final LocalDateTime createdAt;
}
