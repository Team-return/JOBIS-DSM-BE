package com.example.jobis.domain.recruit.facade;

import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.domain.repository.RecruitAreaCodeJpaRepository;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentJpaRepository;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentRepository;
import com.example.jobis.domain.recruit.domain.repository.vo.QueryRecruitAreaCodeVO;
import com.example.jobis.domain.recruit.exception.RecruitNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class RecruitFacade {

    private final RecruitmentJpaRepository recruitmentJpaRepository;
    private final RecruitmentRepository recruitmentRepository;

    public Recruitment getRecruitById(Long id) {
        return recruitmentJpaRepository.findById(id)
                .orElseThrow(() -> RecruitNotFoundException.EXCEPTION);
    }

    public Recruitment getLatestRecruitByCompany(Company company) {
        return company.getRecruitmentList().get(company.getRecruitmentList().size() - 1);
    }

    public Set<String> getJobCodeList(List<RecruitArea> recruitArea) {
        Set<String> res = new HashSet<>();
        for (RecruitArea ra : recruitArea) {
            res.addAll(recruitmentRepository.findAllRecruitCodeByRecruitArea(ra).stream()
                    .map(r -> r.getCodeId().getKeyword()).toList()
            );
        }
        return res;
    }
}
