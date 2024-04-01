package team.retum.jobis.domain.student.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.student.exception.ClassRoomNotFoundException;

@Getter
@Builder
public class SchoolNumber {

    private static final int FIRST_GRADE = 1;
    private static final int SECOND_GRADE = 2;
    private static final int THIRD_GRADE = 3;
    private final Integer grade;
    private final Integer classRoom;
    private final Integer number;

    public static String processSchoolNumber(SchoolNumber schoolNumber) {
        return String.valueOf(schoolNumber.grade)
            + schoolNumber.classRoom
            + (schoolNumber.number < 10 ? "0" + schoolNumber.number : schoolNumber.number);
    }

    public static Department getDepartment(Integer grade, Integer classRoom) {
        if (grade == FIRST_GRADE) {
            return Department.COMMON;
        }

        return switch (classRoom) {
            case 1, 2 -> Department.SOFTWARE_DEVELOP;
            case 3 -> Department.EMBEDDED_SOFTWARE;
            case 4 -> Department.INFORMATION_SECURITY;
            default -> throw ClassRoomNotFoundException.EXCEPTION;
        };
    }

    public static SchoolNumber parseSchoolNumber(String schoolNumber) {
        int grade = Character.getNumericValue(schoolNumber.charAt(0));
        int classRoom = Character.getNumericValue(schoolNumber.charAt(1));
        int number = Character.getNumericValue(schoolNumber.charAt(3));

        if (Character.getNumericValue(schoolNumber.charAt(2)) != 0) {
            number += Character.getNumericValue(schoolNumber.charAt(2)) * 10;
        }

        return SchoolNumber.builder()
            .grade(grade)
            .classRoom(classRoom)
            .number(number)
            .build();
    }
}
