package team.retum.jobis.global.mapper;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@Slf4j
@Component
@Aspect
public class DomainEntityMapAspect {

    @Around("execution(* team.retum.jobis.domain.*.persistence.*PersistenceAdapter.*(..))")
    public Object mapToDomain(ProceedingJoinPoint joinPoint) throws  Throwable {
        Object returningValue = joinPoint.proceed();
        Class<?> entity = returningValue.getClass();
        String domainName = entity.getName()
            .substring(entity.getName().lastIndexOf(".") + 1)
            .replace("Entity", "");
        log.info(domainName);
        Class<?> domain = Class.forName("team.retum.jobis.domain." + domainName.toLowerCase() + ".model." + domainName);
        log.info(domain.getName());
        Constructor<?> domainConstructor = domain.getDeclaredConstructor();
        domainConstructor.setAccessible(true);
        Object domainInstance = domainConstructor.newInstance();
        for (Field field : entity.getDeclaredFields()) {
            field.setAccessible(true);
            Field domainField = domain.getDeclaredField(field.getName());
            domainField.setAccessible(true);
            domainField.set(domainInstance, field.get(returningValue));
        }
        return domainInstance;
    }
}
