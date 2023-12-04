package team.retum.jobis.domain.company.persistence.entity.type;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Manager {

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String managerName;

    @Column(columnDefinition = "VARCHAR(12)", nullable = false)
    private String managerPhoneNo;

    @Column(columnDefinition = "VARCHAR(10)")
    private String subManagerName;

    @Column(columnDefinition = "VARCHAR(12)")
    private String subManagerPhoneNo;

    public void update(String managerName, String managerPhoneNo, String subManagerName, String subManagerPhoneNo) {
        this.managerName = managerName;
        this.managerPhoneNo = managerPhoneNo;
        this.subManagerName = subManagerName;
        this.subManagerPhoneNo = subManagerPhoneNo;
    }
}
