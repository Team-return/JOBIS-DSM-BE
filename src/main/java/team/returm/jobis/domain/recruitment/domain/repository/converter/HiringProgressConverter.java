package team.returm.jobis.domain.recruitment.domain.repository.converter;

import team.returm.jobis.domain.recruitment.domain.enums.ProgressType;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HiringProgressConverter implements AttributeConverter<List<ProgressType>, String> {

    @Override
    public String convertToDatabaseColumn(List<ProgressType> attribute) {
        return attribute.stream().map(Enum::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<ProgressType> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(","))
                .map(ProgressType::valueOf)
                .toList();
    }
}
