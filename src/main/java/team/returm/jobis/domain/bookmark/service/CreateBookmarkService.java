package team.returm.jobis.domain.bookmark.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.bookmark.domain.Bookmark;
import team.returm.jobis.domain.bookmark.domain.repository.BookmarkRepository;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class CreateBookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;

    public void execute(Long recruitmentId) {
        Student student = userFacade.getCurrentStudent();
        Recruitment recruitment = recruitmentRepository.queryRecruitmentById(recruitmentId)
                        .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        bookmarkRepository.saveBookmark(
                new Bookmark(recruitment, student)
        );
    }
}
