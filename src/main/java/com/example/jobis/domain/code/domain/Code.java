package com.example.jobis.domain.code.domain;

import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.controller.dto.response.CodeResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Code {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String keyword;

    @Enumerated(EnumType.STRING)
    private CodeType codeType;
    public CodeResponse to() {
        return new CodeResponse(code, keyword);
    }
}
