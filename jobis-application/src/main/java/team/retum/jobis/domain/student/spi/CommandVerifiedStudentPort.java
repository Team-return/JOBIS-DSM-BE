package team.retum.jobis.domain.student.spi;

public interface CommandVerifiedStudentPort {

    void deleteByGcnAndName(String gcn, String name);
}
