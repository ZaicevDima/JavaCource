package com.zaicev;

public class Main {
    public static void main(String[] args) {
        MyStringBuilder stringBuilder = new MyStringBuilder();
        String testMessage = stringBuilder
                .append('h')
                .append('e')
                .append('l')
                .append('l')
                .append('o')
                .append('!')
                .toString();

        System.out.println(testMessage);

        stringBuilder.undo();
        stringBuilder.undo();
        testMessage = stringBuilder.toString();

        System.out.println(testMessage);

        stringBuilder
                .append('?')
                .undo();

        testMessage = stringBuilder.toString();

        System.out.println(testMessage);

        stringBuilder
                .append('o')
                .append('o')
                .append('o');

        testMessage = stringBuilder.toString();

        System.out.println(testMessage);

        MyStringBuilder test = new MyStringBuilder("123");
        test.append('!');
        System.out.println(test);
    }
}