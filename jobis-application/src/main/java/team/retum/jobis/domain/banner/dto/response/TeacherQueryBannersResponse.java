package team.retum.jobis.domain.banner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.banner.spi.vo.TeacherBannersVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class TeacherQueryBannersResponse {

    private final List<TeacherBannersVO> banners;
}
