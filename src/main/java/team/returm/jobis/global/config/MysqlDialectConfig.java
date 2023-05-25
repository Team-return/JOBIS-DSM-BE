package team.returm.jobis.global.config;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Component;

@Component
public class MysqlDialectConfig extends MySQL8Dialect {
    public MysqlDialectConfig() {
        super.registerFunction(
                "GROUP_CONCAT",
                new StandardSQLFunction("group_concat", StandardBasicTypes.STRING)
        );
    }
}
