package team.returm.jobis.domain.recruitment.service;

import java.util.List;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.returm.jobis.domain.recruitment.presentation.dto.request.ChangeRecruitmentRequest;
import team.returm.jobis.global.annotation.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TeacherChangeRecruitmentStatusService {
    private final RecruitmentRepository recruitmentRepository;

    public void execute(ChangeRecruitmentRequest request) {
        List<Recruitment> recruitments = recruitmentRepository.queryRecruitmentsByIdIn(request.getRecruitmentIds());

        if(recruitments.size() != request.getRecruitmentIds().size()) {
            throw RecruitmentNotFoundException.EXCEPTION;
        }

        recruitmentRepository.saveAllRecruitments(
                recruitments.stream()
                        .map(recruitment -> recruitment.changeStatus(request.getStatus()))
                        .toList()
        );
    }
}
