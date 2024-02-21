package team.retum.jobis.domain.banner.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_banner")
@Entity
public class BannerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(300)")
    private String bannerUrl;


    @NotNull
    @Column(columnDefinition = "DATE")
    private LocalDate startDate;

    @NotNull
    @Column(columnDefinition = "DATE")
    private LocalDate endDate;

    @Builder
    public BannerEntity(Long id, String bannerUrl, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.bannerUrl = bannerUrl;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
