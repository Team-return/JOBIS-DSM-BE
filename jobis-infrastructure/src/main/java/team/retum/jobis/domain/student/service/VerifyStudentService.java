package team.retum.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.student.persistence.repository.VerifiedStudentRepository;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import com.example.jobisapplication.common.annotation.Service;

@RequiredArgsConstructor
@Service
public class VerifyStudentService {

    private final VerifiedStudentRepository verifiedStudentRepository;

    public void execute(String gcn, String name) {
        if (!verifiedStudentRepository.existsVerifiedStudentByGcnAndName(gcn, name)) {
            throw StudentNotFoundException.EXCEPTION;
        }
    }
}
