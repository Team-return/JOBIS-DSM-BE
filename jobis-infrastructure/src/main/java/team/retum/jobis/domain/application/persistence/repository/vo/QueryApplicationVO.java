package team.retum.jobis.domain.application.persistence.repository.vo;

import com.example.jobisapplication.domain.application.model.ApplicationAttachment;
import com.example.jobisapplication.domain.application.spi.vo.ApplicationVO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class QueryApplicationVO extends ApplicationVO {


    @QueryProjection
    public QueryApplicationVO(Long id, String name, Integer grade, Integer number,
                              Integer classNumber, String profileImageUrl, String companyName,
                              List<ApplicationAttachment> applicationAttachments, LocalDateTime createdAt,
                              ApplicationStatus applicationStatus) {
        super(id, name, grade, number, classNumber, profileImageUrl,companyName, applicationAttachments, createdAt, applicationStatus);
    }
}
