package team.retum.jobis.global.config.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 메서드를 호출한 결과를 캐시할 수 있음을 나타내는 주석입니다.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(method = RequestMethod.GET)
@Cacheable
public @interface CacheMapping {

    @AliasFor(annotation = RequestMapping.class)
    String[] path() default {};
}
