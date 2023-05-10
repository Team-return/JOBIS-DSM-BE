package team.returm.jobis.domain.student.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.student.domain.repository.VerifiedStudentRepository;
import team.returm.jobis.domain.student.exception.StudentNotFoundException;
import team.returm.jobis.global.annotation.Service;

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
