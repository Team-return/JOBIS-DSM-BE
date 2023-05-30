package team.returm.jobis.global.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Objects;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidListElements.NotNullElementsValidator.class)
public @interface ValidListElements {

    String message() default "리스트 요소는 null 또는 빈 값을 포함할 수 없습니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class NotNullElementsValidator implements ConstraintValidator<ValidListElements, List<?>> {

        @Override
        public boolean isValid(List value, ConstraintValidatorContext context) {
            if (value == null || value.isEmpty()) {
                return false;
            }

            return value.stream()
                    .allMatch(Objects::nonNull);
        }
    }
}
