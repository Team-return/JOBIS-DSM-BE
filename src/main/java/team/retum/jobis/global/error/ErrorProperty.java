package team.retum.jobis.global.error;

import org.springframework.http.HttpStatus;

public interface ErrorProperty {

    HttpStatus getStatus();

    String getMessage();
}
