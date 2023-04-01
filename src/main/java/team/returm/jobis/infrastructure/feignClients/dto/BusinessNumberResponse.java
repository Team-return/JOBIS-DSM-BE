package team.returm.jobis.infrastructure.feignClients.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BusinessNumberResponse {

    private Integer resultCode;
    private String resultMsg;
    private String totalCount;
    private List<Items> items;
}
