package team.retum.jobis.domain.student.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.persistence.mapper.StudentMapper;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;
import team.retum.jobis.domain.student.spi.StudentPort;

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
    public int queryStudentCountByGradeAndEntranceYear(int grade, int entranceYear) {
        return studentJpaRepository.countByGradeAndEntranceYear(grade, entranceYear);
    }

    @Override
    public boolean existsByGradeAndClassRoomAndNumberAndName(int grade, int classRoom, int number, String name) {
        return studentJpaRepository.existsByGradeAndClassRoomAndNumberAndName(grade, classRoom, number, name);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentMapper.toDomain(
                studentJpaRepository.save(studentMapper.toEntity(student))
        );
    }
}
