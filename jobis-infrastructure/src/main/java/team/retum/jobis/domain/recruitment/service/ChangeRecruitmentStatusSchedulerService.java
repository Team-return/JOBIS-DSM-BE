package team.retum.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.recruitment.persistence.Recruitment;
import team.retum.jobis.domain.recruitment.persistence.enums.RecruitStatus;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import com.example.jobisapplication.common.annotation.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChangeRecruitmentStatusSchedulerService {

    private final RecruitmentRepository recruitmentRepository;

    public void execute() {
        List<Recruitment> recruitments = recruitmentRepository.queryApprovedRecruitmentsAfterRecruitDate();

        recruitmentRepository.saveAllRecruitments(
                recruitments.stream()
                        .map(recruitment -> recruitment.changeStatus(RecruitStatus.RECRUITING))
                        .filter(
                                recruitment ->
                                        recruitment.getRecruitDate().getFinishDate().isBefore(LocalDate.now())
                        )
                        .map(recruitment -> recruitment.changeStatus(RecruitStatus.DONE))
                        .toList()
        );
    }
}
