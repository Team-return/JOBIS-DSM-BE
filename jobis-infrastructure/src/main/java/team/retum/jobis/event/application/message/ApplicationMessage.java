package team.retum.jobis.event.application.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationMessage {
    private final String title;
    private final String content;
}
