package team.returm.jobis.domain.recruitment.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.global.annotation.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChangeRecruitmentStatusSchedulerService {

    private final RecruitmentRepository recruitmentRepository;

    public void execute() {
        List<Recruitment> recruitments = recruitmentRepository.queryRecruitmentsAfterRecruitDate();

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
