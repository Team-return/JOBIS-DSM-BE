package com.example.jobis.domain.application.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateApplicationRequest {

    private List<String> attachmentUrl;
}
