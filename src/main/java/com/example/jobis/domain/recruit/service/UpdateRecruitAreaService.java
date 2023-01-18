package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.repository.RecruitAreaCodeRepository;
import com.example.jobis.domain.code.facade.CodeFacade;
import com.example.jobis.domain.recruit.controller.dto.request.UpdateRecruitAreaRequest;
import com.example.jobis.domain.recruit.domain.Recruit;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.repository.RecruitAreaRepository;
import com.example.jobis.domain.recruit.facade.RecruitAreaFacade;
import com.example.jobis.domain.recruit.facade.RecruitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UpdateRecruitAreaService {

    private final RecruitAreaFacade recruitAreaFacade;
    private final CodeFacade codeFacade;
    private final RecruitAreaRepository recruitAreaRepository;
    private final RecruitAreaCodeRepository recruitAreaCodeRepository;

    @Transactional
    public void execute(UpdateRecruitAreaRequest request, Long recruitAreaId) {

        RecruitArea recruitArea = recruitAreaFacade.getRecruitAreaById(recruitAreaId);
        Recruit recruit = recruitArea.getRecruit();
        List<RecruitAreaCode> recruitAreaCodeList = recruitArea.getCodeList();

        recruitAreaCodeRepository.deleteAll(recruitAreaCodeList);
        recruitAreaRepository.delete(recruitArea);

        RecruitArea newRecruitArea = recruitAreaRepository.save(RecruitArea.builder()
                .recruit(recruit)
                .hiredCount(request.getHiring())
                .majorTask(request.getMajorTask())
                .build());

        List<Long> codes = new ArrayList<>();
        codes.addAll(request.getJob());
        codes.addAll(request.getTech());

        List<Code> codeList = codeFacade.findAllCodeById(codes);
        List<RecruitAreaCode> recruitAreaCodes = codeList.stream()
                .map(code -> new RecruitAreaCode(newRecruitArea, code))
                .toList();
        recruitAreaCodeRepository.saveAll(recruitAreaCodes);
    }
}
