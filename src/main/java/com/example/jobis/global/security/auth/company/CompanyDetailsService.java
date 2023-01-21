package com.example.jobis.global.security.auth.company;

import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyDetailsService implements UserDetailsService {
    private final CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String bizNo) throws UsernameNotFoundException {
        return new CompanyDetails(
                companyRepository.getBizNo(bizNo)
                        .orElseThrow(() -> UserNotFoundException.EXCEPTION)
        );
    }
}