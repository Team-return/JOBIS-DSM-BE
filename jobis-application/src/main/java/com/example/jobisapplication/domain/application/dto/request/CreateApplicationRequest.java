package com.example.jobisapplication.domain.application.dto.request;

import com.example.jobisapplication.domain.application.model.AttachmentType;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateApplicationRequest {

    private List<AttachmentRequest> attachments;

    public static class AttachmentRequest {

        private String url;

        private AttachmentType type;
    }
}
