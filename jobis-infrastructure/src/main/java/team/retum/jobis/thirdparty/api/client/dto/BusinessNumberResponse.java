package team.retum.jobis.thirdparty.api.client.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@JsonNaming(value = PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@NoArgsConstructor
public class BusinessNumberResponse {

    private Integer resultCode;

    private String resultMsg;

    private Integer totalCount;

    private List<Item> items;
}
