package com.example.jobis.domain.teacher.presentaion.dto.response;

import com.example.jobis.domain.company.domain.enums.CompanyType;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruit.domain.repository.vo.QueryRecruitAreaCodeVO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Builder
public class TQueryRecruitmentListResponse {
    private final Long id;
    private final RecruitStatus recruitmentStatus;
    private final String companyName;
    private final CompanyType companyType;
    private final Set<String> recruitmentJob;
    private final Integer recruitmentCount;
    private final int applicationCount;
    private final LocalDate start;
    private final LocalDate end;
    private final boolean militarySupport;


}
