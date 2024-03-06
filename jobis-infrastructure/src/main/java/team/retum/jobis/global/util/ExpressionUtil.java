package team.retum.jobis.global.util;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;

public class ExpressionUtil {

    public static StringExpression groupConcat(Object target) {
        return Expressions.stringTemplate("group_concat({0})", target);
    }
}
