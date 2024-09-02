package team.retum.jobis.domain.intern.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_wintern")
@Entity
public class WinterInternEntity {

    @Id
    @Column(name = "wintern_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_winter_intern", columnDefinition = "TINYINT(1)")
    private boolean isWinterIntern;

    public WinterInternEntity(boolean isWinterIntern) {
        this.isWinterIntern = isWinterIntern;
    }

}
