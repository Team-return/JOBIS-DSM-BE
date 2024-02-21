package team.retum.jobis.domain.banner.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.banner.dto.CreateBannerRequest;
import team.retum.jobis.domain.banner.model.BannerType;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CreateBannerWebRequest {

    @NotNull
    private String bannerUrl;

    @NotNull
    private BannerType bannerType;

    @NotBlank
    private LocalDate startDate;

    @NotBlank
    private LocalDate endDate;

    public CreateBannerRequest toDomainRequest() {
        return CreateBannerRequest.builder()
                .bannerUrl(this.bannerUrl)
                .bannerType(this.bannerType)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .build();
    }
}
