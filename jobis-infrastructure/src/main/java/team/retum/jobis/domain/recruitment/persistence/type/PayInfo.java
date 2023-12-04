package team.retum.jobis.domain.recruitment.persistence.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PayInfo {

    @NotNull
    @Column(columnDefinition = "INT")
    private Integer trainPay;

    @Column(columnDefinition = "VARCHAR(20)")
    private String pay;
}
