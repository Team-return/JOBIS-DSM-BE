package team.retum.jobis.domain.student.spi;

public interface CommandVerifiedStudentPort {
    void deleteVerifiedStudentByGcnAndName(String gcn, String name);
}
