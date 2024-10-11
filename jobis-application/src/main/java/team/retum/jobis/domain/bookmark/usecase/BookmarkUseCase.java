package team.retum.jobis.domain.bookmark.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.bookmark.model.Bookmark;
import team.retum.jobis.domain.bookmark.spi.CommandBookmarkPort;
import team.retum.jobis.domain.bookmark.spi.QueryBookmarkPort;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.student.model.Student;

@RequiredArgsConstructor
@UseCase
public class BookmarkUseCase {

    private final QueryBookmarkPort queryBookmarkPort;
    private final CommandBookmarkPort commandBookmarkPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SecurityPort securityPort;

    public void execute(Long recruitmentId) {
        Student student = securityPort.getCurrentStudent();
        Recruitment recruitment = queryRecruitmentPort.getByIdOrThrow(recruitmentId);

        queryBookmarkPort.getByRecruitmentIdAndStudentId(recruitment.getId(), student.getId())
            .ifPresentOrElse(
                commandBookmarkPort::delete,
                () -> commandBookmarkPort.save(Bookmark.builder()
                    .studentId(student.getId())
                    .recruitmentId(recruitment.getId())
                    .build())
            );
    }
}
