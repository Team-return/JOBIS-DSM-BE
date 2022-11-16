package com.example.jobis.domain.recruit.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TechList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId @JoinColumn(name = "area_id")
    private RecruitArea recruitArea;

    @Column(length = 3, nullable = false)
    private String techCode;
}
