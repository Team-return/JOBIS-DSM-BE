package team.retum.jobis.domain.bookmark.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.bookmark.model.Bookmark;
import team.retum.jobis.domain.bookmark.spi.CommandBookmarkPort;
import team.retum.jobis.domain.bookmark.spi.QueryBookmarkPort;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.QueryStudentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class BookmarkUseCase {

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
