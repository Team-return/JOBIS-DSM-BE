package com.example.jobis.domain.code.presentation;

import com.example.jobis.domain.code.presentation.dto.response.JobCodeResponse;
import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.presentation.dto.response.CodeResponse;
import com.example.jobis.domain.code.service.FindCodeService;
import com.example.jobis.domain.code.service.FindJobCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/code")
public class CodeController {
    private final FindCodeService findCodeService;
    private final FindJobCodeService findJobCodeService;

    @GetMapping("/tech")
    public List<CodeResponse> findTechCode(@RequestParam(value = "keyword", required = false) String keyword) {
        return findCodeService.execute(keyword, CodeType.TECH);
    }

    @GetMapping("/job")
    public List<JobCodeResponse> findAllJobCodes() {
        return findJobCodeService.execute();
    }

}
