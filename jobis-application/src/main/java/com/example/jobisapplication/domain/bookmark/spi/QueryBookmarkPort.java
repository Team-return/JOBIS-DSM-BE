package com.example.jobisapplication.domain.bookmark.spi;

import com.example.jobisapplication.domain.bookmark.model.Bookmark;
import com.example.jobisapplication.domain.bookmark.spi.vo.StudentBookmarksVO;

import java.util.List;
import java.util.Optional;

public interface QueryBookmarkPort {

    boolean existsBookmarkByRecruitmentAndStudent(Long recruitmentId, Long studentId);

    Optional<Bookmark> queryBookmarkByRecruitmentIdAndStudentId(Long recruitmentId, Long studentId);

    List<StudentBookmarksVO> queryBookmarksByStudentId(Long studentId);
}
