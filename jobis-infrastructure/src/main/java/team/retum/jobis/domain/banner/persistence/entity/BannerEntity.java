package team.retum.jobis.domain.banner.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import team.retum.jobis.domain.banner.model.BannerType;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_banner")
@Entity
@Builder
@AllArgsConstructor
public class BannerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(100)")
    private String title;

    @NotNull
    @Column(columnDefinition = "VARCHAR(500)")
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(12)")
    private BannerType bannerType;

    @NotNull
    @Column(columnDefinition = "DATE")
    private LocalDate startDate;

    @NotNull
    @Column(columnDefinition = "DATE")
    private LocalDate endDate;

    @Column(columnDefinition = "BIGINT")
    private Long detailId;
}
