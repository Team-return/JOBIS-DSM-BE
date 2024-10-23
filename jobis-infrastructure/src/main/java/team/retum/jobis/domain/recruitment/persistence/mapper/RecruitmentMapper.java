package team.retum.jobis.domain.recruitment.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyJpaRepository;
import team.retum.jobis.domain.recruitment.model.RecruitingPeriod;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.model.Salary;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;

@RequiredArgsConstructor
@Component
public class RecruitmentMapper {

    private final CompanyJpaRepository companyJpaRepository;

    public RecruitmentEntity toEntity(Recruitment domain) {
        CompanyEntity company = companyJpaRepository.getReferenceById(domain.getCompanyId());

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
            .startDate(domain.getRecruitingPeriod() == null ? null : domain.getRecruitingPeriod().startDate())
            .endDate(domain.getRecruitingPeriod() == null ? null : domain.getRecruitingPeriod().endDate())
            .requiredGrade(domain.getRequiredGrade())
            .requiredLicenses(domain.getRequiredLicenses())
            .pay(domain.getSalary().pay())
            .submitDocument(domain.getSubmitDocument())
            .trainPay(domain.getSalary().trainingPay())
            .submitDocument(domain.getSubmitDocument())
            .workingHours(domain.getWorkingHours())
            .flexibleWorking(domain.isFlexibleWorking())
            .winterIntern(domain.isWinterIntern())
            .isConvertible(domain.isConvertible())
            .build();
    }

    public Recruitment toDomain(RecruitmentEntity entity) {
        RecruitingPeriod recruitingPeriod = null;
        if (entity.getRecruitDate() != null) {
            recruitingPeriod = new RecruitingPeriod(
                entity.getRecruitDate().getStartDate(),
                entity.getRecruitDate().getFinishDate()
            );
        }
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
            .recruitingPeriod(recruitingPeriod)
            .salary(new Salary(
                entity.getPayInfo().getTrainPay(),
                entity.getPayInfo().getPay()
            ))
            .workingHours(entity.getWorkingHours())
            .flexibleWorking(entity.isFlexibleWorking())
            .requiredGrade(entity.getRequiredGrade())
            .requiredLicenses(entity.getRequiredLicenses())
            .submitDocument(entity.getSubmitDocument())
            .submitDocument(entity.getSubmitDocument())
            .winterIntern(entity.isWinterIntern())
            .isConvertible(entity.isConvertible())
            .createdAt(entity.getCreatedAt())
            .build();
    }
}
