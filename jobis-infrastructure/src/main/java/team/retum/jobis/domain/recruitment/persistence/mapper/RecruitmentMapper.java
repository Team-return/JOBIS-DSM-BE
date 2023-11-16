package team.retum.jobis.domain.recruitment.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyJpaRepository;
import team.retum.jobis.domain.recruitment.model.Recruitment;
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
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .requiredGrade(domain.getRequiredGrade())
                .requiredLicenses(domain.getRequiredLicenses())
                .pay(domain.getPay())
                .submitDocument(domain.getSubmitDocument())
                .trainPay(domain.getTrainPay())
                .submitDocument(domain.getSubmitDocument())
                .startTime(domain.getStartTime())
                .endTime(domain.getEndTime())
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
                .startDate(entity.getRecruitDate().getStartDate())
                .endDate(entity.getRecruitDate().getFinishDate())
                .requiredGrade(entity.getRequiredGrade())
                .requiredLicenses(entity.getRequiredLicenses())
                .pay(entity.getPayInfo().getPay())
                .submitDocument(entity.getSubmitDocument())
                .trainPay(entity.getPayInfo().getTrainPay())
                .submitDocument(entity.getSubmitDocument())
                .startTime(entity.getWorkingHour().getStartTime())
                .endTime(entity.getWorkingHour().getEndTime())
                .winterIntern(entity.isWinterIntern())
                .build();
    }
}
