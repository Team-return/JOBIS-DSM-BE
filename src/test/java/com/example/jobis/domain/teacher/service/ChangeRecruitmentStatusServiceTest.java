package com.example.jobis.domain.teacher.service;

import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruit.facade.RecruitFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class ChangeRecruitmentStatusServiceTest {

    @MockBean
    private RecruitFacade recruitFacade;
    private ChangeRecruitmentStatusService changeRecruitmentStatusService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        changeRecruitmentStatusService = new ChangeRecruitmentStatusService(recruitFacade);
    }

    @Test
    void execute() {
        //given
        UUID uuid = UUID.randomUUID();
        Recruitment recruitment = Recruitment.builder()
                .status(RecruitStatus.REQUESTED)
                .build();
        given(recruitFacade.getRecruitById(uuid)).willReturn(recruitment);

        //when
        changeRecruitmentStatusService.execute(uuid, RecruitStatus.READY);


        assertEquals(recruitment.getStatus(), RecruitStatus.READY);
    }
}