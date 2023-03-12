package com.example.jobis.domain.recruitment.service;

import com.example.jobis.domain.code.presentation.dto.request.CreateRecruitAreaRequest;
import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.facade.CodeFacade;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.recruitment.domain.RecruitArea;
import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.domain.repository.RecruitAreaRepository;
import com.example.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import com.example.jobis.domain.recruitment.facade.RecruitFacade;
import com.example.jobis.domain.user.domain.User;
import com.example.jobis.domain.user.domain.enums.Authority;
import com.example.jobis.domain.user.domain.repository.UserRepository;
import com.example.jobis.domain.user.exception.UserNotFoundException;
import com.example.jobis.domain.user.facade.UserFacade;
import com.example.jobis.global.annotation.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class CreateRecruitAreaService {

    private final RecruitAreaRepository recruitAreaRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final CompanyFacade companyFacade;
    private final CodeFacade codeFacade;
    private final RecruitFacade recruitFacade;
    private final UserFacade userFacade;

    public void execute(CreateRecruitAreaRequest request, UUID recruitmentId) {
        User user = userFacade.getCurrentUser();

        Recruitment recruitment = recruitFacade.queryRecruitmentById(recruitmentId);
        if(user.getAuthority() == Authority.COMPANY) {
            Company company = companyFacade.queryCompanyById(user.getId());

            recruitment.checkCompany(company.getId());
        }

        RecruitArea recruitArea = recruitAreaRepository.save(
                RecruitArea.builder()
                        .majorTask(request.getMajorTask())
                        .hiredCount(request.getHiring())
                        .recruitment(recruitment)
                        .build()
        );

        List<Code> codes = codeFacade.findAllCodeById(
                Stream.of(request.getJobCodes(), request.getTechCodes())
                        .flatMap(Collection::stream)
                        .toList()
        );

        recruitmentRepository.saveAllRecruitAreaCodes(
                codeFacade.generateRecruitAreaCode(recruitArea, codes)
        );
    }
}
