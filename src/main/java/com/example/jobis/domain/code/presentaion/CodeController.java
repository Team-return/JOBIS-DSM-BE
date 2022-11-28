package com.example.jobis.domain.code.presentaion;

import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.presentaion.dto.response.CodeResponse;
import com.example.jobis.domain.code.service.FindCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/code")
public class CodeController {
    private final FindCodeService findCodeService;

    @GetMapping("/tech")
    public List<CodeResponse> findTechCode(@RequestParam("keyword") String keyword) {
        return findCodeService.execute(keyword, CodeType.TECH);
    }

    @GetMapping("/licenses")
    public List<CodeResponse> findLicenseCode(@RequestParam("keyword") String keyword) {
        return findCodeService.execute(keyword, CodeType.LICENSE);
    }

}
