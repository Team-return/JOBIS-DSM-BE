package team.retum.jobis.domain.code.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.common.util.StringUtil;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.spi.QueryCodePort;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetKeywordsService {

    private final QueryCodePort queryCodePort;

    public String getKeywordsAsJoinedString(String jobCodes) {
        return StringUtil.joinStringList(
                queryCodePort.queryCodesByIdIn(
                        StringUtil.divideString(jobCodes, ",").stream().map(Long::parseLong).toList()
                ).stream().map(Code::getKeyword).toList(),
                "/"
        );
    }

    public List<String> getKeywordsAsList(List<String> jobCodes) {
        return queryCodePort.queryCodesByIdIn(
                jobCodes.stream().map(Long::parseLong).toList()
        ).stream().map(Code::getKeyword).toList();
    }
}
