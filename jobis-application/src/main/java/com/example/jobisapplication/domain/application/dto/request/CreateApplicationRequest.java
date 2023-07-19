package com.example.jobisapplication.domain.application.dto.request;

import com.example.jobisapplication.domain.application.model.AttachmentType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateApplicationRequest {

    private List<AttachmentRequest> attachments;

    @Getter
    @NoArgsConstructor
    public static class AttachmentRequest {

        private String url;

        private AttachmentType type;
    }
}
