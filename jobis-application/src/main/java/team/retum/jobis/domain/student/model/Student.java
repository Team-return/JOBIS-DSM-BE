package team.retum.jobis.domain.student.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;
import team.retum.jobis.domain.interest.model.Interest;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

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

    private final Set<Interest> interests;

    @Builder(toBuilder = true)
    public Student(Long id, String name, SchoolNumber schoolNumber, Gender gender,
                   Department department, String profileImageUrl, Integer entranceYear, Set<Interest> interests) {
        this.id = id;
        this.name = name;
        this.schoolNumber = schoolNumber;
        this.gender = gender;
        this.department = department;
        this.profileImageUrl = profileImageUrl;
        this.entranceYear = entranceYear == null ? getEntranceYear(schoolNumber.getGrade()) : entranceYear;
        this.interests = interests == null ? new HashSet<>() : interests;

    }

    public static Integer getEntranceYear(Integer grade) {
        int year = Year.now().getValue();
        return switch (grade) {
            case 2 -> year - 1;
            case 3 -> year - 2;
            default -> year;
        };
    }

    public Student changeStudentProfile(String profileImageUrl) {
        return this.toBuilder()
            .profileImageUrl(profileImageUrl)
            .build();
    }

    public boolean getApplicable(boolean winterIntern) {
        if (winterIntern && this.schoolNumber.getGrade().equals(2)) {
            return true;
        }
        return !winterIntern && this.schoolNumber.getGrade().equals(3);
    }
}
