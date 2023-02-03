package com.example.jobis.domain.company.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CompanyAttachment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_attachment_id")
    private Long id;

    @NotNull
    private String attachmentUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Builder
    public CompanyAttachment(String attachmentUrl, Company company) {
        this.attachmentUrl = attachmentUrl;
        this.company = company;
    }
}
