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

import static team.retum.jobis.domain.application.persistence.entity.QApplicationEntity.applicationEntity;
import static team.retum.jobis.domain.interest.persistence.entity.QInterestEntity.interestEntity;
import static team.retum.jobis.domain.student.persistence.entity.QStudentEntity.studentEntity;

@RequiredArgsConstructor
@Repository
public class StudentPersistenceAdapter implements StudentPort {

    private final StudentJpaRepository studentJpaRepository;
    private final StudentMapper studentMapper;
    private final JPAQueryFactory queryFactory;

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

    @Override
    public List<Student> getByInterestCode(List<Long> codeIds) {
        return queryFactory
            .select(studentEntity)
            .from(studentEntity)
            .join(interestEntity)
            .on(interestEntity.student.id.eq(studentEntity.id))
            .join(interestEntity.code)
            .where(interestEntity.code.code.in(codeIds))
            .fetch()
            .stream()
            .map(studentMapper::toDomain)
            .toList();
    }

    @Override
    public Long getTotalStudentsByClassNumber(Integer classNum) {
        return queryFactory
            .select(studentEntity.count())
            .from(studentEntity)
            .where(studentEntity.classRoom.eq(classNum))
            .fetchOne();
    }


    @Override
    public Long getPassedStudentsByClassNumber(Integer classNum) {
        List<Long> counts = queryFactory
            .select(studentEntity.countDistinct())
            .from(studentEntity)
            .join(applicationEntity)
            .on(
                applicationEntity.student.eq(studentEntity),
                applicationEntity.applicationStatus.in(ApplicationStatus.PASS, ApplicationStatus.FIELD_TRAIN)
            )
            .where(studentEntity.classRoom.eq(classNum))
            .fetch();

        return null != counts ? counts.get(0) : 0;
    }
}
