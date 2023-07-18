package team.retum.jobis.domain.code.persistence.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import com.example.jobisapplication.domain.code.domain.CodeType;

import java.util.List;

import static team.retum.jobis.domain.code.persistence.QCode.code;

@RequiredArgsConstructor
@Repository
public class CodeRepository {

    private final CodeJpaRepository codeJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public List<CodeEntity> queryCodesByKeywordAndType(String keyword, CodeType codeType, Long parentCode) {
        return jpaQueryFactory
                .selectFrom(code)
                .where(
                        code.codeType.eq(codeType),
                        containsKeyword(keyword),
                        eqParentCode(parentCode)
                ).fetch();
    }

    public List<CodeEntity> queryCodesByIdIn(List<Long> codeIds) {
        return codeJpaRepository.findCodesByIdIn(codeIds);
    }

    //==conditions==//

    private BooleanExpression containsKeyword(String keyword) {
        return keyword == null ? null : code.keyword.contains(keyword);
    }

    private BooleanExpression eqParentCode(Long parentCode) {
        return parentCode == null ? null : code.parentCode.id.eq(parentCode);
    }
}