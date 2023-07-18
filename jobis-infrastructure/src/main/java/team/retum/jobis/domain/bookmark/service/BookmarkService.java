package team.retum.jobis.domain.bookmark.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.bookmark.domain.Bookmark;
import team.retum.jobis.domain.bookmark.domain.repository.BookmarkRepository;
import team.retum.jobis.domain.recruitment.domain.Recruitment;
import team.retum.jobis.domain.recruitment.facade.RecruitmentFacade;
import team.retum.jobis.domain.student.domain.Student;
import team.retum.jobis.domain.user.facade.UserFacade;
import team.retum.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final RecruitmentFacade recruitmentFacade;
    private final UserFacade userFacade;

    public void execute(Long recruitmentId) {
        Student student = userFacade.getCurrentStudent();
        Recruitment recruitment = recruitmentFacade.queryRecruitmentById(recruitmentId);

        bookmarkRepository.queryBookmarkByRecruitmentAndStudent(recruitment, student)
                .ifPresentOrElse(
                        bookmarkRepository::deleteBookmark,
                        () -> bookmarkRepository.saveBookmark(new Bookmark(recruitment, student))
                );
    }
}
