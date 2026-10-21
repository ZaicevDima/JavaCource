package com.zaicev;

import java.util.List;
import java.util.stream.Collectors;

public class StreamAPI {
    enum DayOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    static class WorkSheet {
        String name;
        List<DayOfWeek> workDays;

        public WorkSheet(String name, List<DayOfWeek> workDays) {
            this.name = name;
            this.workDays = workDays;
        }

        public String getName() {
            return name;
        }

        public List<DayOfWeek> getWorkDays() {
            return workDays;
        }
    }

    public static void test() {
        var denSheet = new WorkSheet("Денис", List.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SUNDAY));
        var benSheet = new WorkSheet("Бен", List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.SATURDAY));
        var lisSheet = new WorkSheet("Лиза", List.of(DayOfWeek.THURSDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY));
        var sheets = List.of(denSheet, benSheet, lisSheet);

        // DONE: Необходимо вывести на экран дни в которые работало более 1 человека
        sheets.stream()
                .map(sheet -> sheet.workDays)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .forEach(entry -> System.out.println(entry.getKey()));
    }
}
