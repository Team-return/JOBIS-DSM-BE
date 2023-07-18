package team.retum.jobis.domain.bookmark.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.bookmark.persistence.BookmarkEntity;
import team.retum.jobis.domain.bookmark.persistence.repository.BookmarkRepository;
import team.retum.jobis.domain.recruitment.persistence.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.facade.RecruitmentFacade;
import team.retum.jobis.domain.student.persistence.StudentEntity;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

@RequiredArgsConstructor
@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final RecruitmentFacade recruitmentFacade;
    private final UserFacade userFacade;

    public void execute(Long recruitmentId) {
        StudentEntity studentEntity = userFacade.getCurrentStudent();
        RecruitmentEntity recruitmentEntity = recruitmentFacade.queryRecruitmentById(recruitmentId);

        bookmarkRepository.queryBookmarkByRecruitmentAndStudent(recruitmentEntity, studentEntity)
                .ifPresentOrElse(
                        bookmarkRepository::deleteBookmark,
                        () -> bookmarkRepository.saveBookmark(new BookmarkEntity(recruitmentEntity, studentEntity))
                );
    }
}
