package com.example.jobis.domain.recruit.domain.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Pay {

    @Column(columnDefinition = "INT", nullable = false)
    private Integer trainPay;

    @Column(columnDefinition = "INT")
    private Integer pay;
}
