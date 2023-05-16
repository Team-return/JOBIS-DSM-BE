package team.returm.jobis.infrastructure.feignClients.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BusinessNumberResponse {

    private Integer resultCode;
    private String resultMsg;
    private String totalCount;
    private List<Items> items;
}
