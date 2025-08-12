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
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_winter_intern")
@Entity
public class WinterInternEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_winter_intern", columnDefinition = "TINYINT(1)")
    private boolean isWinterIntern;

    public WinterInternEntity(boolean isWinterIntern) {
        this.isWinterIntern = isWinterIntern;
    }
}
