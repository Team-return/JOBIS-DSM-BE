package team.retum.jobis.global.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import team.retum.jobis.global.security.auth.ThreadLocalService;

@Component
@RequiredArgsConstructor
@Aspect
public class ThreadLocalAspect {

    private final ThreadLocalService<?> threadLocalService;

    @AfterReturning("execution(* team.retum.jobis.domain.*.presentation.*WebAdapter.*(..))")
    public void clearThreadLocal() {
        threadLocalService.remove();
    }
}
