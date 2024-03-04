package team.retum.jobis.domain.student.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

import java.time.Year;

@Getter
@Aggregate
public class Student {

    private final Long id;

    private final String name;

    private final SchoolNumber schoolNumber;

    private final Gender gender;

    private final Department department;

    private final String profileImageUrl;

    private final Integer entranceYear;

    @Builder(toBuilder = true)
    public Student(Long id, String name, SchoolNumber schoolNumber, Gender gender,
                   Department department, String profileImageUrl, Integer entranceYear) {
        this.id = id;
        this.name = name;
        this.schoolNumber = schoolNumber;
        this.gender = gender;
        this.department = department;
        this.profileImageUrl = profileImageUrl;
        this.entranceYear = entranceYear == null ? getEntranceYear(schoolNumber.getGrade()) : entranceYear;
    }

    public Student changeStudentProfile(String profileImageUrl) {
        return this.toBuilder()
                .profileImageUrl(profileImageUrl)
                .build();
    }

    private Integer getEntranceYear(Integer grade) {
        int year = Year.now().getValue();
        return switch (grade) {
            case 2 -> year - 1;
            case 3 -> year - 2;
            default -> year;
        };
    }

    public boolean getApplicable(boolean winterIntern) {
        if (winterIntern && this.schoolNumber.getGrade().equals(2)) {
            return true;
        }
        return !winterIntern && this.schoolNumber.getGrade().equals(3);
    }
}
