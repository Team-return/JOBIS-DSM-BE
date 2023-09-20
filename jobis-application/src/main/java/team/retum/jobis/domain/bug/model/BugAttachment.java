package team.retum.jobis.domain.bug.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BugAttachment {

    private final String attachmentUrl;

    public static BugAttachment of(String attachmentUrl) {
        return new BugAttachment(attachmentUrl);
    }
}
