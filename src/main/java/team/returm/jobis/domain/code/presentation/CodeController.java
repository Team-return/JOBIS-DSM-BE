package team.returm.jobis.domain.code.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.presentation.dto.response.CodesResponse;
import team.returm.jobis.domain.code.service.QueryCodesService;
import team.returm.jobis.global.util.StringUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/codes")
public class CodeController {

    private final QueryCodesService codesService;

    @GetMapping
    public CodesResponse getCodes(
            @RequestParam(value = "code_type") CodeType codeType,
            @RequestParam(value = "keyword", required = false) String keyword
    )  {
        keyword = StringUtil.nullToEmpty(keyword);
        return codesService.execute(keyword, codeType);
    }
}
