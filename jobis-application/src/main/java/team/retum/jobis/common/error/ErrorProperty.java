package team.retum.jobis.common.error;

public interface ErrorProperty {

    HttpStatus getStatus();

    String getMessage();
}
