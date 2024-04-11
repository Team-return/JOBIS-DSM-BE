package team.retum.jobis.domain.student.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.student.model.SchoolNumber;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.persistence.mapper.StudentMapper;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;
import team.retum.jobis.domain.student.spi.StudentPort;

import java.util.List;
import java.util.Optional;

import static team.retum.jobis.domain.application.persistence.entity.QApplicationEntity.applicationEntity;
import static team.retum.jobis.domain.student.persistence.entity.QStudentEntity.studentEntity;

@RequiredArgsConstructor
@Repository
public class StudentPersistenceAdapter implements StudentPort {

    private final StudentJpaRepository studentJpaRepository;
    private final StudentMapper studentMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Student> queryStudentById(Long studentId) {
        return studentJpaRepository.findById(studentId)
            .map(studentMapper::toDomain);
    }

    @Override
    public boolean existsByGradeAndClassRoomAndNumberAndEntranceYear(SchoolNumber schoolNumber, int entranceYear) {
        return studentJpaRepository.existsByGradeAndClassRoomAndNumberAndEntranceYear(
            schoolNumber.getGrade(),
            schoolNumber.getClassRoom(),
            schoolNumber.getNumber(),
            entranceYear
        );
    }

    @Override
    public int getCountByGradeAndEntranceYear(int grade, int entranceYear) {
        return studentJpaRepository.countByGradeAndEntranceYear(grade, entranceYear);
    }

    @Override
    public boolean existsBySchoolNumberAndName(SchoolNumber schoolNumber, String name) {
        return studentJpaRepository.existsByGradeAndClassRoomAndNumberAndName(
            schoolNumber.getGrade(), schoolNumber.getClassRoom(), schoolNumber.getNumber(), name
        );
    }

    @Override
    public Long getCountByApplicationStatus(List<ApplicationStatus> statuses) {
        return queryFactory
            .select(studentEntity.countDistinct())
            .from(studentEntity)
            .join(applicationEntity)
            .on(
                applicationEntity.student.eq(studentEntity),
                applicationEntity.applicationStatus.in(statuses)
            )
            .where(studentEntity.grade.eq(3))
            .fetchOne();
    }

    @Override
    public Student save(Student student) {
        return studentMapper.toDomain(
            studentJpaRepository.save(studentMapper.toEntity(student))
        );
    }
}
