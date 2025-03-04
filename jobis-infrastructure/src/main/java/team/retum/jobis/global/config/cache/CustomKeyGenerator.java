package team.retum.jobis.global.config.cache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return method.getName() + "_" +SimpleKeyGenerator.generateKey(params);
    }
}
