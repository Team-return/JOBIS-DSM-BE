package team.retum.jobis.global.security;

import com.example.jobisapplication.common.spi.SecurityPort;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityAdapter implements SecurityPort {
    @Override
    public Long getCurrentUserId() {
        return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
