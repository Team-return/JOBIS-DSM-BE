package team.retum.jobis.domain.code.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.code.dto.response.CodesResponse;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.usecase.QueryCodesService;
import team.retum.jobis.global.config.cache.CacheMapping;

import static team.retum.jobis.global.config.cache.CacheName.CODE;

@CacheConfig(cacheNames = CODE)
@RequiredArgsConstructor
@RequestMapping("/codes")
@RestController
public class CodeWebAdapter {

    private final QueryCodesService codesService;

    @CacheMapping
    public CodesResponse getCodes(
            @RequestParam(value = "type") CodeType codeType,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "parent_code", required = false) Long parentCode
    ) {
        return codesService.execute(keyword, codeType, parentCode);
    }
}
