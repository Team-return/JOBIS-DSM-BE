package team.returm.jobis.domain.bookmark.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.returm.jobis.domain.bookmark.presentation.dto.response.QueryStudentBookmarksResponse;
import team.returm.jobis.domain.bookmark.service.CreateBookmarkService;
import team.returm.jobis.domain.bookmark.service.DeleteBookmarkService;
import team.returm.jobis.domain.bookmark.service.QueryStudentBookmarksService;

@RequiredArgsConstructor
@RequestMapping("/bookmarks")
@RestController
public class BookmarkController {

    private final CreateBookmarkService createBookmarkService;
    private final DeleteBookmarkService deleteBookmarkService;
    private final QueryStudentBookmarksService queryStudentBookmarksService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{recruitment-id}")
    public void createBookmark(
            @PathVariable("recruitment-id") Long recruitmentId
    ) {
        createBookmarkService.execute(recruitmentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{recruitment-id}")
    public void deleteBookmark(
            @PathVariable("recruitment-id") Long recruitmentId
    ) {
        deleteBookmarkService.execute(recruitmentId);
    }

    @GetMapping
    public QueryStudentBookmarksResponse queryStudentBookmarks() {
        return queryStudentBookmarksService.execute();
    }
}
