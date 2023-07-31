package com.example.jobisapplication.domain.application.dto.request;

import com.example.jobisapplication.domain.application.model.AttachmentType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
public class CreateApplicationRequest {

    private List<AttachmentRequest> attachments;

    @Getter
    @Builder
    public static class AttachmentRequest {

        private String url;

        private AttachmentType type;
    }
}
