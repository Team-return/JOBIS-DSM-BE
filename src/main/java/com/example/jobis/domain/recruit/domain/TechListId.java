package com.example.jobis.domain.recruit.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class TechListId implements Serializable {

    private Long areaId;

    @Column(nullable = false)
    private Long techCode;
}
