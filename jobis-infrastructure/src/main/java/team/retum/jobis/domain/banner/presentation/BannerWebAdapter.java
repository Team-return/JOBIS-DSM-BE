package team.retum.jobis.domain.banner.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.banner.dto.response.QueryBannersResponse;
import team.retum.jobis.domain.banner.dto.response.TeacherQueryBannersResponse;
import team.retum.jobis.domain.banner.presentation.dto.CreateBannerWebRequest;
import team.retum.jobis.domain.banner.usecase.CreateBannerUseCase;
import team.retum.jobis.domain.banner.usecase.DeleteBannerUseCase;
import team.retum.jobis.domain.banner.usecase.QueryBannersUseCase;
import team.retum.jobis.domain.banner.usecase.TeacherQueryBannersUseCase;

@RequiredArgsConstructor
@RequestMapping("/banners")
@RestController
public class BannerWebAdapter {

    private final CreateBannerUseCase createBannerUseCase;
    private final DeleteBannerUseCase deleteBannerUseCase;
    private final QueryBannersUseCase queryBannersUseCase;
    private final TeacherQueryBannersUseCase queryBannerListUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{detail-id}")
    public void createBanner(@RequestBody @Valid CreateBannerWebRequest request,
                             @PathVariable("detail-id") Long detailId
    ) {
        createBannerUseCase.execute(request.toDomainRequest(), detailId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{banner-id}")
    public void deleteBanner(
        @PathVariable("banner-id") Long bannerId
    ) {
        deleteBannerUseCase.execute(bannerId);
    }

    @GetMapping
    public QueryBannersResponse queryBanners() {
        return queryBannersUseCase.execute();
    }

    @GetMapping("/teacher")
    public TeacherQueryBannersResponse queryBannerList(
        @RequestParam(value = "is_opened") Boolean isOpened
    ) {
        return queryBannerListUseCase.execute(isOpened);
    }
}
