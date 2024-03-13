package team.retum.jobis.domain.recruitment.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class RecruitmentAreaElement {
    private final List<String> jobs;
    private final int hiring;
}
