package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import com.example.jobisapplication.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.presentation.dto.request.ChangeRecruitmentRequest;
import com.example.jobisapplication.common.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherChangeRecruitmentStatusService {
    private final RecruitmentRepository recruitmentRepository;

    public void execute(ChangeRecruitmentRequest request) {
        List<RecruitmentEntity> recruitmentEntities = recruitmentRepository.queryRecruitmentsByIdIn(request.getRecruitmentIds());

        if (recruitmentEntities.size() != request.getRecruitmentIds().size()) {
            throw RecruitmentNotFoundException.EXCEPTION;
        }

        recruitmentRepository.saveAllRecruitments(
                recruitmentEntities.stream()
                        .map(recruitment -> recruitment.changeStatus(request.getStatus()))
                        .toList()
        );
    }
}
