package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.recruitment.domain.Recruitment;
import team.retum.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.presentation.dto.request.ChangeRecruitmentRequest;
import team.retum.jobis.global.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherChangeRecruitmentStatusService {
    private final RecruitmentRepository recruitmentRepository;

    public void execute(ChangeRecruitmentRequest request) {
        List<Recruitment> recruitments = recruitmentRepository.queryRecruitmentsByIdIn(request.getRecruitmentIds());

        if (recruitments.size() != request.getRecruitmentIds().size()) {
            throw RecruitmentNotFoundException.EXCEPTION;
        }

        recruitmentRepository.saveAllRecruitments(
                recruitments.stream()
                        .map(recruitment -> recruitment.changeStatus(request.getStatus()))
                        .toList()
        );
    }
}
