package com.example.jobisapplication.domain.application.dto.response;

import com.example.jobisapplication.domain.application.model.ApplicationAttachment;
import com.example.jobisapplication.domain.application.model.AttachmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AttachmentResponse {
    private String url;

    private AttachmentType type;

    public static AttachmentResponse of(ApplicationAttachment applicationAttachment) {
        return new AttachmentResponse(
                applicationAttachment.getAttachmentUrl(),
                applicationAttachment.getType()
        );
    }
}
