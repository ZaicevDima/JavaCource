package com.zaicev;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Integer[] arrTest1 = {1, 2, 3, 4, 3, 2, 5, 10};
        FrequencyAnalysis<Integer> frequencyAnalysis1 = new FrequencyAnalysis<>();
        Map<Integer, Integer> frequency1 = frequencyAnalysis1.getFrequencyAnalysis(arrTest1);
        frequencyAnalysis1.print(frequency1);


        Character[] arrTest2 = {'1', '2', '3', '4', '3', '2', '5', '2'};
        FrequencyAnalysis<Character> frequencyAnalysis2 = new FrequencyAnalysis<>();
        Map<Character, Integer> frequency2 = frequencyAnalysis2.getFrequencyAnalysis(arrTest2);
        frequencyAnalysis2.print(frequency2);

        String[] arrTest3 = {"asd", "123", "czx", "asd", "asd"};
        FrequencyAnalysis<String> frequencyAnalysis3 = new FrequencyAnalysis<>();
        Map<String, Integer> frequency3 = frequencyAnalysis3.getFrequencyAnalysis(arrTest3);
        frequencyAnalysis3.print(frequency3);

    }
}