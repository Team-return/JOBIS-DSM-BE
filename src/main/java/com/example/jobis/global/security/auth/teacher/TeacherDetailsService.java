package com.example.jobis.global.security.auth.teacher;

import com.example.jobis.domain.user.domain.User;
import com.example.jobis.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        User teacher = userRepository.findByAccountId(accountId);
        return new TeacherDetails(teacher.getAccountId());
    }
}
