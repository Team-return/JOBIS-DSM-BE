package com.example.jobisapplication.domain.recruitment.spi;

import com.example.jobisapplication.domain.recruitment.dto.RecruitmentFilter;
import com.example.jobisapplication.domain.recruitment.dto.response.RecruitAreaResponse;
import com.example.jobisapplication.domain.recruitment.model.RecruitArea;
import com.example.jobisapplication.domain.recruitment.model.Recruitment;
import com.example.jobisapplication.domain.recruitment.spi.vo.RecruitmentDetailVO;
import com.example.jobisapplication.domain.recruitment.spi.vo.RecruitmentVO;

import java.util.List;
import java.util.Optional;

public interface QueryRecruitmentPort {
    boolean existsOnRecruitmentByCompanyId(Long companyId);

    List<Recruitment> queryAllRecruitments();

    Optional<Recruitment> queryRecruitmentById(Long recruitmentId);

    Optional<RecruitArea> queryRecruitmentAreaById(Long recruitAreaId);

    Long queryRecruitmentAreaCountByRecruitmentId(Long recruitmentId);

    Optional<Recruitment> queryRecentRecruitmentByCompanyId(Long companyId);

    RecruitmentDetailVO queryRecruitmentDetailById(Long recruitmentId);

    List<RecruitmentVO> queryRecruitmentsByFilter(RecruitmentFilter filter);

    List<Recruitment> queryRecruitmentsByIdIn(List<Long> recruitmentIds);

    Long getRecruitmentCountByFilter(RecruitmentFilter filter);

    List<RecruitAreaResponse> queryRecruitAreasByRecruitmentId(Long recruitmentId);

}
