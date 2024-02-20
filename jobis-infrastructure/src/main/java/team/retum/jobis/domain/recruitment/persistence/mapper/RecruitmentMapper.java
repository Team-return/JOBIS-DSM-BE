package team.retum.jobis.domain.recruitment.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyJpaRepository;
import team.retum.jobis.domain.recruitment.model.RecruitingPeriod;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.model.Salary;
import team.retum.jobis.domain.recruitment.model.WorkingHours;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;

@RequiredArgsConstructor
@Component
public class RecruitmentMapper {

    private final CompanyJpaRepository companyJpaRepository;

    public RecruitmentEntity toEntity(Recruitment domain) {
        CompanyEntity company = companyJpaRepository.findById(domain.getCompanyId())
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        return RecruitmentEntity.builder()
                .id(domain.getId())
                .companyEntity(company)
                .etc(domain.getEtc())
                .recruitYear(domain.getRecruitYear())
                .benefits(domain.getBenefits())
                .hiringProgress(domain.getHiringProgress())
                .personalContact(domain.isPersonalContract())
                .militarySupport(domain.isMilitarySupport())
                .status(domain.getStatus())
                .startDate(domain.getRecruitingPeriod().startDate())
                .endDate(domain.getRecruitingPeriod().endDate())
                .requiredGrade(domain.getRequiredGrade())
                .requiredLicenses(domain.getRequiredLicenses())
                .pay(domain.getSalary().pay())
                .submitDocument(domain.getSubmitDocument())
                .trainPay(domain.getSalary().trainingPay())
                .submitDocument(domain.getSubmitDocument())
                .startTime(domain.getWorkingHours().startTime())
                .endTime(domain.getWorkingHours().endTime())
                .winterIntern(domain.isWinterIntern())
                .build();
    }

    public Recruitment toDomain(RecruitmentEntity entity) {
        return Recruitment.builder()
                .id(entity.getId())
                .companyId(entity.getCompany().getId())
                .etc(entity.getEtc())
                .recruitYear(entity.getRecruitYear())
                .benefits(entity.getBenefits())
                .hiringProgress(entity.getHiringProgress())
                .personalContract(entity.isPersonalContact())
                .militarySupport(entity.isMilitarySupport())
                .status(entity.getStatus())
                .recruitingPeriod(new RecruitingPeriod(
                        entity.getRecruitDate().getStartDate(),
                        entity.getRecruitDate().getFinishDate()
                ))
                .salary(new Salary(
                        entity.getPayInfo().getTrainPay(),
                        entity.getPayInfo().getPay()
                ))
                .workingHours(new WorkingHours(
                        entity.getWorkingHour().getStartTime(),
                        entity.getWorkingHour().getEndTime()
                ))
                .requiredGrade(entity.getRequiredGrade())
                .requiredLicenses(entity.getRequiredLicenses())
                .submitDocument(entity.getSubmitDocument())
                .submitDocument(entity.getSubmitDocument())
                .winterIntern(entity.isWinterIntern())
                .build();
    }
}
