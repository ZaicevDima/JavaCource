package com.zaicev;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FrequencyAnalysis<T> {

    public Map<T, Integer> getFrequencyAnalysis(T[] array) {
        Map<T, Integer> result = new HashMap<>();

        Arrays.stream(array)
                .forEach(item -> result.put(item, result.getOrDefault(item, 0) + 1));

        return result;
    }

    public void print(Map<T, Integer> frequency) {
        System.out.println("Frequency: ");
        frequency
                .forEach((key, value) -> System.out.println(key + " " + value));

        System.out.println();
    }
}
