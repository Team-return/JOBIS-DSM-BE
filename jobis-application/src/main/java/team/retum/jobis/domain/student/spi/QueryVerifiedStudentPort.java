package team.retum.jobis.domain.student.spi;

public interface QueryVerifiedStudentPort {
    boolean existsVerifiedStudentByGcnAndName(String gcn, String name);
}
