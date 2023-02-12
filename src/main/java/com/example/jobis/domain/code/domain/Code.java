package com.example.jobis.domain.code.domain;

import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.controller.dto.response.CodeResponse;
import com.example.jobis.domain.code.domain.enums.JobType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@BatchSize(size = 100)
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Code {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    @Enumerated(EnumType.STRING)
    private CodeType codeType;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    public CodeResponse to() {
        return CodeResponse.builder()
                .code(id)
                .keyword(keyword)
                .build();
    }
}
