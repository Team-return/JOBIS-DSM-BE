package com.example.jobis.domain.code.service;

import com.example.jobis.domain.code.controller.dto.request.CreateRecruitAreaCodeRequest;
import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.repository.RecruitAreaCodeRepository;
import com.example.jobis.domain.code.facade.CodeFacade;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.facade.RecruitAreaFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CreateRecruitAreaCodeService {

    private final RecruitAreaCodeRepository recruitAreaCodeRepository;
    private final RecruitAreaFacade recruitAreaFacade;
    private final CodeFacade codeFacade;

    public void execute(UUID recruitAreaId, CreateRecruitAreaCodeRequest request) {

        RecruitArea recruitArea = recruitAreaFacade.getRecruitAreaById(recruitAreaId);
        List<Code> codeList = codeFacade.findAllCodeById(request.getCodeList());

        List<RecruitAreaCode> recruitAreaCodeList = codeList.stream()
                .map(code -> new RecruitAreaCode(recruitArea, code))
                .toList();

        recruitAreaCodeRepository.saveAll(recruitAreaCodeList);
    }
}
