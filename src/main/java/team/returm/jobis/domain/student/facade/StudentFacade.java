package team.returm.jobis.domain.student.facade;

import team.returm.jobis.domain.student.domain.repository.StudentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentFacade {

    private final StudentJpaRepository studentJpaRepository;

    public boolean existsEmail(String email) {
        return studentJpaRepository.existsByEmail(email);
    }
}
