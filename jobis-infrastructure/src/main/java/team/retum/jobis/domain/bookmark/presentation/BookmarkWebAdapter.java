package team.retum.jobis.domain.bookmark.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.jobisapplication.domain.bookmark.dto.response.QueryStudentBookmarksResponse;
import com.example.jobisapplication.domain.bookmark.usecase.BookmarkService;
import com.example.jobisapplication.domain.bookmark.usecase.QueryStudentBookmarksService;

@RequiredArgsConstructor
@RequestMapping("/bookmarks")
@RestController
public class BookmarkWebAdapter {

    private final BookmarkService bookmarkService;
    private final QueryStudentBookmarksService queryStudentBookmarksService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{recruitment-id}")
    public void bookmark(
            @PathVariable("recruitment-id") Long recruitmentId
    ) {
        bookmarkService.execute(recruitmentId);
    }

    @GetMapping
    public QueryStudentBookmarksResponse queryStudentBookmarks() {
        return queryStudentBookmarksService.execute();
    }
}