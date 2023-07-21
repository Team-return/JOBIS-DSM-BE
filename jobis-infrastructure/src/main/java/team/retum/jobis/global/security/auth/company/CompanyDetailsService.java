package team.retum.jobis.global.security.auth.company;

import com.example.jobisapplication.domain.company.model.Company;
import com.example.jobisapplication.domain.company.spi.QueryCompanyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyDetailsService implements UserDetailsService {

    private final QueryCompanyPort queryCompanyPort;

    @Override
    public UserDetails loadUserByUsername(String companyId) throws UsernameNotFoundException {
        Company company = queryCompanyPort.queryCompanyById(
                Long.valueOf(companyId)
        );

        return new CompanyDetails(company.getId());
    }
}