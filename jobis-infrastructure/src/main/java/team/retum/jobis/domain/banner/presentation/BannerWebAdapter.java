package team.retum.jobis.domain.banner.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import team.retum.jobis.domain.banner.presentation.dto.CreateBannerWebRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import team.retum.jobis.domain.banner.usecase.CreateBannerUseCase;

@RequiredArgsConstructor
@RequestMapping("/banners")
@RestController
public class BannerWebAdapter {

    private final CreateBannerUseCase createBannerUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createBanner(@RequestBody @Valid CreateBannerWebRequest request) {
        createBannerUseCase.execute(request.toDomainRequest());
    }
}
