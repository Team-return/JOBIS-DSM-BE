package com.example.jobisapplication.domain.bug.spi.vo;

import com.example.jobisapplication.domain.bug.model.DevelopmentArea;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BugReportsVO {
    private final Long id;
    private final String title;
    private final DevelopmentArea developmentArea;
    private final LocalDateTime createdAt;
}
