package team.retum.jobis.domain.student.persistence;

import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.StudentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.student.persistence.mapper.StudentMapper;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class StudentPersistenceAdapter implements StudentPort {

    private final StudentJpaRepository studentJpaRepository;
    private final StudentMapper studentMapper;

    @Override
    public Optional<Student> queryStudentById(Long studentId) {
        return studentJpaRepository.findById(studentId)
                .map(studentMapper::toDomain);
    }

    @Override
    public boolean existsByGradeAndClassRoomAndNumber(int grade, int classRoom, int number) {
        return studentJpaRepository.existsByGradeAndClassRoomAndNumber(grade, classRoom, number);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentMapper.toDomain(
                studentJpaRepository.save(studentMapper.toEntity(student))
        );
    }
}
