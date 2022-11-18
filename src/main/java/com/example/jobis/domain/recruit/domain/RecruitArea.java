package com.example.jobis.domain.recruit.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecruitArea {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_area_id")
    private Long id;

    private Integer hiredCount;

    private String details;

    @Column(length = 3)
    private String jobCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id", nullable = false)
    private Recruit recruit;

    @OneToMany(mappedBy = "recruitArea")
    private List<TechList> techList = new ArrayList<>();

    @Builder
    public RecruitArea(Integer hiredCount, String details, String jobCode, Recruit recruit) {
        this.hiredCount = hiredCount;
        this.details = details;
        this.jobCode = jobCode;
        this.recruit = recruit;
    }

}
