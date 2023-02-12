package com.example.jobis.domain.recruit.domain.repository;

<<<<<<< HEAD
import com.example.jobis.domain.recruit.domain.Recruit;
=======
import com.example.jobis.domain.recruit.domain.Recruitment;
>>>>>>> main
import com.example.jobis.domain.recruit.domain.RecruitArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
<<<<<<< HEAD

public interface RecruitAreaRepository extends JpaRepository<RecruitArea, Long> {

    List<RecruitArea> findAllByRecruit(Recruit recruit);
=======
import java.util.Optional;
import java.util.UUID;

public interface RecruitAreaRepository extends JpaRepository<RecruitArea, UUID> {

    Optional<RecruitArea> findByIdAndRecruitment(UUID id, Recruitment recruitment);
>>>>>>> main
}
