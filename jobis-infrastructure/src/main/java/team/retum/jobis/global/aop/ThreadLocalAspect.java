package team.retum.jobis.global.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import team.retum.jobis.global.security.auth.CurrentUserHolder;

@Component
@RequiredArgsConstructor
@Aspect
public class ThreadLocalAspect {

    private final CurrentUserHolder<?> currentUserHolder;

    @AfterReturning("execution(* team.retum.jobis.domain.*.presentation.*WebAdapter.*(..))")
    public void clearThreadLocal() {
        currentUserHolder.remove();
    }
}
