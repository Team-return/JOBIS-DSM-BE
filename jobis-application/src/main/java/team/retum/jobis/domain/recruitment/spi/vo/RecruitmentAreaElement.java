package team.retum.jobis.domain.recruitment.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecruitmentAreaElement {
    private final List<String> jobs;
    private final int hiring;
}
