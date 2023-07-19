package com.example.jobisapplication.domain.bookmark.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.bookmark.model.Bookmark;
import com.example.jobisapplication.domain.bookmark.spi.CommandBookmarkPort;
import com.example.jobisapplication.domain.bookmark.spi.QueryBookmarkPort;
import com.example.jobisapplication.domain.recruitment.model.Recruitment;
import com.example.jobisapplication.domain.student.model.Student;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class BookmarkService {

    private final QueryBookmarkPort queryBookmarkPort;
    private final CommandBookmarkPort commandBookmarkPort;
    private final RecruitmentFacade recruitmentFacade;
    private final UserFacade userFacade;

    public void execute(Long recruitmentId) {
        Student student = userFacade.getCurrentStudent();
        Recruitment recruitment = recruitmentFacade.queryRecruitmentById(recruitmentId);

        queryBookmarkPort.queryBookmarkByRecruitmentIdAndStudentId(recruitment.getId(), student.getId())
                .ifPresentOrElse(
                        commandBookmarkPort::deleteBookmark,
                        () -> commandBookmarkPort.saveBookmark(Bookmark.builder()
                                .studentId(student.getId())
                                .recruitmentId(recruitment.getId())
                                .build())
                );
    }
}
