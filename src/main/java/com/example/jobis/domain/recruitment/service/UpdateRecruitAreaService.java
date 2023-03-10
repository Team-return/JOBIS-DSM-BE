package com.example.jobis.domain.recruitment.service;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.facade.CodeFacade;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.recruitment.controller.dto.request.UpdateRecruitAreaRequest;
import com.example.jobis.domain.recruitment.domain.RecruitArea;
import com.example.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import com.example.jobis.domain.recruitment.facade.RecruitAreaFacade;
import com.example.jobis.domain.user.domain.User;
import com.example.jobis.domain.user.domain.enums.Authority;
import com.example.jobis.domain.user.domain.repository.UserRepository;
import com.example.jobis.domain.user.exception.UserNotFoundException;
import com.example.jobis.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class UpdateRecruitAreaService {

    private final RecruitAreaFacade recruitAreaFacade;
    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;
    private final CodeFacade codeFacade;
    private final UserRepository userRepository;
    private final CompanyFacade companyFacade;

    @Transactional
    public void execute(UpdateRecruitAreaRequest request, UUID recruitAreaId) {
        UUID currentUserId = userFacade.getCurrentUserId();
        User user = userRepository.queryUserById(currentUserId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        RecruitArea recruitArea = recruitAreaFacade.getRecruitAreaById(recruitAreaId);

        if (user.getAuthority() == Authority.COMPANY) {
            Company company = companyFacade.queryCompanyById(user.getId());
            recruitArea.getRecruitment().checkCompany(company.getId());
        }

        recruitmentRepository.deleteRecruitAreaCodeByRecruitAreaId(recruitArea.getId());
        List<Code> codes = codeFacade.findAllCodeById(
                Stream.of(request.getJobCodes(), request.getTechCodes())
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
        );

        recruitArea.update(request.getHiring(), request.getMajorTask());
        recruitmentRepository.saveAllRecruitAreaCodes(
                codeFacade.generateRecruitAreaCode(recruitArea, codes)
        );
    }
}
