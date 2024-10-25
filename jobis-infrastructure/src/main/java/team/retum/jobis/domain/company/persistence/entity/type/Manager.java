package team.retum.jobis.domain.company.persistence.entity.type;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Manager {

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String managerName;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String representativePhoneNo;

    public void update(String managerName, String representativePhoneNo) {
        this.managerName = managerName;
        this.representativePhoneNo = representativePhoneNo;
    }
}
