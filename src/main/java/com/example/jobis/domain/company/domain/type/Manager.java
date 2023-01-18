package com.example.jobis.domain.company.domain.type;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Manager {

    @NotNull
    @Column(columnDefinition = "VARCHAR(10)")
    private String managerName;

    @NotNull
    @Column(columnDefinition = "VARCHAR(11)")
    private String managerPhoneNo;

    @NotNull
    @Column(columnDefinition = "VARCHAR(10)")
    private String subManagerName;

    @NotNull
    @Column(columnDefinition = "VARCHAR(11)")
    private String subManagerPhoneNo;

    public void update(String managerName, String managerPhoneNo, String subManagerName, String subManagerPhoneNo) {
        this.managerName = managerName;
        this.managerPhoneNo = managerPhoneNo;
        this.subManagerName = subManagerName;
        this.subManagerPhoneNo = subManagerPhoneNo;
    }
}
