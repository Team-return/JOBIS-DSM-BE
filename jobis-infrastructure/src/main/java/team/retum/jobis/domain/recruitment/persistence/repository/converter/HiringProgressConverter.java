package team.retum.jobis.domain.recruitment.persistence.repository.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
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
