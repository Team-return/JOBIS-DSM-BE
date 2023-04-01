package team.returm.jobis.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.company.domain.repository.CompanyRepository;
import team.returm.jobis.domain.company.exception.CompanyNotFoundException;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.student.domain.repository.StudentRepository;
import team.returm.jobis.domain.student.exception.StudentNotFoundException;
import team.returm.jobis.domain.user.domain.User;
import team.returm.jobis.domain.user.domain.repository.UserJpaRepository;
import team.returm.jobis.domain.user.domain.repository.UserRepository;
import team.returm.jobis.domain.user.exception.UserNotFoundException;


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

    public Student getCurrentStudent() {
        return studentRepository.queryStudentById(
                this.getCurrentUserId()
        ).orElseThrow(() -> StudentNotFoundException.EXCEPTION);
    }

    public Company getCurrentCompany() {
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
