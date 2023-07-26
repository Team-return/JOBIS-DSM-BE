package com.example.jobisapplication.domain.recruitment.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.recruitment.dto.request.ChangeRecruitmentStatusRequest;
import com.example.jobisapplication.domain.recruitment.exception.RecruitmentNotFoundException;
import com.example.jobisapplication.domain.recruitment.model.Recruitment;
import com.example.jobisapplication.domain.recruitment.spi.CommandRecruitmentPort;
import com.example.jobisapplication.domain.recruitment.spi.QueryRecruitmentPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class TeacherChangeRecruitmentStatusUseCase {

    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryRecruitmentPort queryRecruitmentPort;

    public void execute(ChangeRecruitmentStatusRequest request) {
        List<Recruitment> recruitments = queryRecruitmentPort.queryRecruitmentsByIdIn(request.getRecruitmentIds());

        if (recruitments.size() != request.getRecruitmentIds().size()) {
            throw RecruitmentNotFoundException.EXCEPTION;
        }

        commandRecruitmentPort.saveAllRecruitments(
                recruitments.stream()
                        .map(recruitment -> recruitment.changeStatus(request.getStatus()))
                        .toList()
        );
    }
}
