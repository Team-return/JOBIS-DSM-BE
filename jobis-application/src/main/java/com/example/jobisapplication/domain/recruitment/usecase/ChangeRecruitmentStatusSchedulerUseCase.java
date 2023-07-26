package com.example.jobisapplication.domain.recruitment.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.recruitment.model.RecruitStatus;
import com.example.jobisapplication.domain.recruitment.model.Recruitment;
import com.example.jobisapplication.domain.recruitment.spi.CommandRecruitmentPort;
import com.example.jobisapplication.domain.recruitment.spi.QueryRecruitmentPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ChangeRecruitmentStatusSchedulerUseCase {

    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryRecruitmentPort queryRecruitmentPort;

    public void execute() {
        List<Recruitment> recruitments = queryRecruitmentPort.queryAllRecruitments();

        commandRecruitmentPort.saveAllRecruitments(
                recruitments.stream()
                        .filter(recruitment -> recruitment.getStartDate().isAfter(LocalDate.now()))
                        .map(recruitment -> recruitment.changeStatus(RecruitStatus.RECRUITING))
                        .filter(recruitment -> recruitment.getEndDate().isAfter(LocalDate.now()))
                        .map(recruitment -> recruitment.changeStatus(RecruitStatus.DONE))
                        .toList()
        );
    }
}
