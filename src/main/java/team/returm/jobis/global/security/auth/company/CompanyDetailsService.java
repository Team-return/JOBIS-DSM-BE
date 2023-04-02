package team.returm.jobis.global.security.auth.company;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.company.domain.repository.CompanyRepository;
import team.returm.jobis.domain.company.exception.CompanyNotFoundException;

@Component
@RequiredArgsConstructor
public class CompanyDetailsService implements UserDetailsService {
    private final CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String companyId) throws UsernameNotFoundException {
        Company company = companyRepository.queryCompanyById(
                Long.valueOf(companyId)
        ).orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        return new CompanyDetails(company.getId());
    }
}