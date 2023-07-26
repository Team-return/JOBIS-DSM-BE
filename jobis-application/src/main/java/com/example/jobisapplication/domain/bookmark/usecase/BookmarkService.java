package com.example.jobisapplication.domain.bookmark.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.bookmark.model.Bookmark;
import com.example.jobisapplication.domain.bookmark.spi.CommandBookmarkPort;
import com.example.jobisapplication.domain.bookmark.spi.QueryBookmarkPort;
import com.example.jobisapplication.domain.recruitment.exception.RecruitmentNotFoundException;
import com.example.jobisapplication.domain.recruitment.model.Recruitment;
import com.example.jobisapplication.domain.recruitment.spi.QueryRecruitmentPort;
import com.example.jobisapplication.domain.student.exception.StudentNotFoundException;
import com.example.jobisapplication.domain.student.model.Student;
import com.example.jobisapplication.domain.student.spi.QueryStudentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class BookmarkService {

    private final QueryBookmarkPort queryBookmarkPort;
    private final CommandBookmarkPort commandBookmarkPort;
    private final QueryStudentPort queryStudentPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SecurityPort securityPort;

    public void execute(Long recruitmentId) {
        Long currentUserId = securityPort.getCurrentUserId();
        Student student = queryStudentPort.queryStudentById(currentUserId)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        Recruitment recruitment = queryRecruitmentPort.queryRecruitmentById(recruitmentId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

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
