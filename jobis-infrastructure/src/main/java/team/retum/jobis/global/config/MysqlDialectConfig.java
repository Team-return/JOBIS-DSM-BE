package team.retum.jobis.global.config;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Component;

@Component
public class MysqlDialectConfig extends MySQL8Dialect {
    public MysqlDialectConfig() {
        super.registerFunction(
                "GROUP_CONCAT",
                new SQLFunctionTemplate(StandardBasicTypes.STRING, "group_concat(distinct ?1)")
        );
    }
}
