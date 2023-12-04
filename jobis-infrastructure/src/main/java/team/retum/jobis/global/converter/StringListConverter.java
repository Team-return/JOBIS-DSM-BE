package team.retum.jobis.global.converter;

import team.retum.jobis.common.util.StringUtil;

import jakarta.persistence.AttributeConverter;
import java.util.List;

public class StringListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return StringUtil.joinStringList(attribute, ",");
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return StringUtil.divideString(dbData, ",");
    }
}
