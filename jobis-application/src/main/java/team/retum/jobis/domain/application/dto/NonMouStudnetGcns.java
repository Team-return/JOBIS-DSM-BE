package team.retum.jobis.domain.application.dto;


import lombok.Builder;
import lombok.Getter;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;


@Getter
public class NonMouStudnetGcns {

    private final List<Integer> grade;
    private final List<Integer> classRoom;
    private final List<Integer> number;
    private final List<Integer> entranceYear;

    @Builder
    public NonMouStudnetGcns(List<Integer> grade, List<Integer> classRoom, List<Integer> number) {
        this.grade = grade;
        this.classRoom = classRoom;
        this.number = number;
        this.entranceYear = grade.stream()
                .map(NonMouStudnetGcns::getEntranceYear)
                .collect(Collectors.toList());
    }


    public static Integer getEntranceYear(Integer grade) {
        int year = Year.now().getValue();
        return switch (grade) {
            case 2 -> year - 1;
            case 3 -> year - 2;
            default -> year;
        };
    }
}

