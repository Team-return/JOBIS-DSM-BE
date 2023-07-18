package team.retum.jobis.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.company.persistence.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyRepository;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.student.persistence.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentRepository;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.user.persistence.User;
import team.retum.jobis.domain.user.persistence.repository.UserJpaRepository;
import team.retum.jobis.domain.user.persistence.repository.UserRepository;
import team.retum.jobis.domain.user.exception.UserNotFoundException;


@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final UserJpaRepository userJpaRepository;

    public User getUser(String accountId) {
        return userRepository.queryUserByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public StudentEntity getCurrentStudent() {
        return studentRepository.queryStudentById(
                this.getCurrentUserId()
        ).orElseThrow(() -> StudentNotFoundException.EXCEPTION);
    }

    public CompanyEntity getCurrentCompany() {
        return companyRepository.queryCompanyById(
                this.getCurrentUserId()
        ).orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

    public User getCurrentUser() {
        return userRepository.queryUserById(
                this.getCurrentUserId()
        ).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public Long getCurrentUserId() {
        return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
