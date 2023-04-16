package team.returm.jobis.domain.code.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.domain.RecruitAreaCode;
import team.returm.jobis.domain.code.domain.enums.CodeType;

import java.util.List;

import static team.returm.jobis.domain.code.domain.QRecruitAreaCode.recruitAreaCode;
import static team.returm.jobis.domain.code.domain.QCode.code;

@RequiredArgsConstructor
@Repository
public class CodeRepository {

    private final CodeJpaRepository codeJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public List<RecruitAreaCode> queryCodesByKeywordsAndCodeType(List<String> keywords, CodeType codeType) {
        return jpaQueryFactory
                .selectFrom(recruitAreaCode)
                .where(
                        inKeywords(keywords),
                        recruitAreaCode.codeType.eq(codeType)
                ).fetch();
    }

    public List<Code> queryTechCodesByKeywordContaining(String keyword) {
        return jpaQueryFactory
                .selectFrom(code)
                .where(
                        code.keyword.contains(keyword),
                        code.codeType.eq(CodeType.TECH)
                ).fetch();
    }

    public List<Code> queryJobCodes() {
        return codeJpaRepository.queryJobCodes();
    }

    //==conditions==//

    private BooleanExpression inKeywords(List<String> keywords) {
        return keywords == null ? null : recruitAreaCode.codeKeyword.in(keywords);
    }
}