package team.retum.jobis.domain.bookmark.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.bookmark.dto.response.QueryStudentBookmarksResponse;
import team.retum.jobis.domain.bookmark.usecase.BookmarkUseCase;
import team.retum.jobis.domain.bookmark.usecase.QueryStudentBookmarksUseCase;

import static team.retum.jobis.global.config.cache.CacheName.RECRUITMENT;

@RequiredArgsConstructor
@RequestMapping("/bookmarks")
@RestController
public class BookmarkWebAdapter {

    private final BookmarkUseCase bookmarkUseCase;
    private final QueryStudentBookmarksUseCase queryStudentBookmarksUseCase;

    @CacheEvict(cacheNames = RECRUITMENT, allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{recruitment-id}")
    public void bookmark(
            @PathVariable("recruitment-id") Long recruitmentId
    ) {
        bookmarkUseCase.execute(recruitmentId);
    }

    @GetMapping
    public QueryStudentBookmarksResponse queryStudentBookmarks() {
        return queryStudentBookmarksUseCase.execute();
    }
}
