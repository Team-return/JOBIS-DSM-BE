package team.retum.jobis.global.security.auth.company;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyJpaRepository;
import team.retum.jobis.global.exception.InvalidTokenException;

import static team.retum.jobis.global.config.cache.CacheName.COMPANY_USER;

@CacheConfig(cacheNames = COMPANY_USER)
@Component
@RequiredArgsConstructor
public class CompanyDetailsService implements UserDetailsService {

    private final CompanyJpaRepository companyJpaRepository;

    @Cacheable
    @Override
    public UserDetails loadUserByUsername(String companyId) throws UsernameNotFoundException {
        CompanyEntity company = companyJpaRepository.findById(Long.valueOf(companyId))
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);

        return new CompanyDetails(company);
    }
}