package team.retum.jobis.domain.code.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.jobisapplication.domain.code.model.CodeType;
import com.example.jobisapplication.domain.code.dto.response.CodesResponse;
import team.retum.jobis.domain.code.service.QueryCodesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/codes")
public class CodeController {

    private final QueryCodesService codesService;

    @GetMapping
    public CodesResponse getCodes(
            @RequestParam(value = "type") CodeType codeType,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "parent_code", required = false) Long parentCode
    ) {
        return codesService.execute(keyword, codeType, parentCode);
    }
}
