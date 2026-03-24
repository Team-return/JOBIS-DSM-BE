package team.retum.jobis.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

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

    boolean allowDuplicates() default true;

    class NotNullElementsValidator implements ConstraintValidator<ValidListElements, List<?>> {

        boolean allowDuplicates = true;

        @Override
        public void initialize(ValidListElements constraintAnnotation) {
            this.allowDuplicates = constraintAnnotation.allowDuplicates();
        }

        @Override
        public boolean isValid(List value, ConstraintValidatorContext context) {
            if (value == null || value.isEmpty()) {
                return false;
            }

            if (!allowDuplicates && value.stream().distinct().count() != value.size()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("중복된 요소가 포함되어 있습니다.")
                    .addConstraintViolation();
                return false;
            }

            return value.stream()
                .allMatch(Objects::nonNull);
        }
    }
}
