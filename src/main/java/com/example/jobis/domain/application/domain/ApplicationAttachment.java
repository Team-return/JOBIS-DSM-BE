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

    @Id
    @NotNull
    private String attachmentUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @Builder
    public ApplicationAttachment(String attachmentUrl, Application application) {
        this.attachmentUrl = attachmentUrl;
        this.application = application;
    }
}
