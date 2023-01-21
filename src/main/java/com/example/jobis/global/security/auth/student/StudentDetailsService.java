package com.example.jobis.global.security.auth.student;

import com.example.jobis.domain.student.domain.Student;
import com.example.jobis.domain.student.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentDetailsService implements UserDetailsService {
    private final StudentRepository studentRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email);
        return new StudentDetails(student.getEmail());
    }
}
