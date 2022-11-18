package com.example.jobis.domain.recruit.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TechList {
    @EmbeddedId
    private TechListId id;

    @MapsId("areaId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_area_id")
    private RecruitArea recruitArea;


}
