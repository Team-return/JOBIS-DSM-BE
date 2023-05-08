package team.returm.jobis.domain.bookmark.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.bookmark.domain.Bookmark;
import team.returm.jobis.domain.bookmark.domain.repository.BookmarkRepository;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.facade.RecruitmentFacade;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class CreateBookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final RecruitmentFacade recruitmentFacade;
    private final UserFacade userFacade;

    public void execute(Long recruitmentId) {
        Student student = userFacade.getCurrentStudent();
        Recruitment recruitment = recruitmentFacade.queryRecruitmentById(recruitmentId);

        bookmarkRepository.saveBookmark(
                new Bookmark(recruitment, student)
        );
    }
}
