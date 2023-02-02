package com.example.jobis.domain.application.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ApplicationAttachment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_attachment_id")
    private Long id;

    @NotNull
    private String attachmentUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @Builder
    public ApplicationAttachment(String attachmentUrl, Application application) {
        this.attachmentUrl = attachmentUrl;
        this.application = application;
    }
}
