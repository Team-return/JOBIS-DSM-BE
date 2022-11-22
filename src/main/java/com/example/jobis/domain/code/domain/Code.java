package com.example.jobis.domain.code.domain;

import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.presentaion.dto.response.CodeResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Code {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String keyword;

    private CodeType codeType;
    public CodeResponse to() {
        return new CodeResponse(code, keyword);
    }
}
