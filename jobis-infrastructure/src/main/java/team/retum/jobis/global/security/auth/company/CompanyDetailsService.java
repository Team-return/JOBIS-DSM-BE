package team.retum.jobis.global.security.auth.company;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyJpaRepository;
import team.retum.jobis.global.exception.InvalidTokenException;
import team.retum.jobis.global.security.auth.CurrentUserHolder;

@Component
@RequiredArgsConstructor
public class CompanyDetailsService implements UserDetailsService {

    private final CompanyJpaRepository companyJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String companyId) throws UsernameNotFoundException {
        CompanyEntity company = companyJpaRepository.findById(
                Long.valueOf(companyId)
        ).orElseThrow(() -> InvalidTokenException.EXCEPTION);
        CurrentUserHolder.setUser(company);

        return new CompanyDetails(company.getId());
    }
}