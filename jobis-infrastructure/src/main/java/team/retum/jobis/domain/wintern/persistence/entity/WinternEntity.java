package team.retum.jobis.domain.wintern.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_wintern")
@Entity
public class WinternEntity {

    @Id
    @Column(name = "wintern_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_winter_intern", columnDefinition = "TINYINT(1)")
    private boolean isWinterIntern;

    public WinternEntity(boolean isWinterIntern) {
        this.isWinterIntern = isWinterIntern;
    }

}
