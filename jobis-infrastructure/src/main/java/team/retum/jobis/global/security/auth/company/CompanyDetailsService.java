package team.retum.jobis.global.security.auth.company;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyRepository;
import team.retum.jobis.global.exception.InvalidTokenException;

@Component
@RequiredArgsConstructor
public class CompanyDetailsService implements UserDetailsService {
    private final CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String companyId) throws UsernameNotFoundException {
        CompanyEntity companyEntity = companyRepository.queryCompanyById(
                Long.valueOf(companyId)
        ).orElseThrow(() -> InvalidTokenException.EXCEPTION);

        return new CompanyDetails(companyEntity.getId());
    }
}