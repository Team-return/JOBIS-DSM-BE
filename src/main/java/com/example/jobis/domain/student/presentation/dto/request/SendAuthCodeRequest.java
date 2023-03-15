package com.example.jobis.domain.student.presentation.dto.request;

import com.example.jobis.global.util.RegexProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class SendAuthCodeRequest {

    @Pattern(regexp = RegexProperty.EMAIL)
    private String email;
}
