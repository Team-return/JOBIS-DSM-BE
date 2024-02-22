package team.retum.jobis.domain.banner.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.retum.jobis.domain.banner.presentation.dto.CreateBannerWebRequest;
import team.retum.jobis.domain.banner.usecase.CreateBannerUseCase;
import team.retum.jobis.domain.banner.usecase.DeleteBannerUseCase;

@RequiredArgsConstructor
@RequestMapping("/banners")
@RestController
public class BannerWebAdapter {

    private final CreateBannerUseCase createBannerUseCase;
    private final DeleteBannerUseCase deleteBannerUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createBanner(@RequestBody @Valid CreateBannerWebRequest request) {
        createBannerUseCase.execute(request.toDomainRequest());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{banner-id}")
    public void deleteBanner(
            @PathVariable("banner-id") Long bannerId
    ) {
        deleteBannerUseCase.execute(bannerId);
    }
}
