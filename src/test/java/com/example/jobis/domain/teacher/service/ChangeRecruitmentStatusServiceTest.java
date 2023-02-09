package com.example.jobis.domain.teacher.service;

import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruit.facade.RecruitFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ChangeRecruitmentStatusServiceTest {

    @Mock
    private RecruitFacade recruitFacade;
    @InjectMocks
    private ChangeRecruitmentStatusService changeRecruitmentStatusService;

    @Test
    void execute() {
        //given
        Recruitment recruitment = Recruitment.builder()
                .status(RecruitStatus.REQUESTED)
                .build();
        given(recruitFacade.getRecruitById(0L)).willReturn(recruitment);

        //when
        changeRecruitmentStatusService.execute(0L, RecruitStatus.READY);


        assertEquals(recruitment.getStatus(), RecruitStatus.READY);
    }
}