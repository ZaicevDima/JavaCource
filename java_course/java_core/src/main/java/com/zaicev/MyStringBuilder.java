package com.zaicev;

import java.util.Arrays;
import java.util.Stack;

public class MyStringBuilder {

    private Stack<Memento> history;

    private static final char[] EMPTY_VALUE = new char[0];

    public MyStringBuilder() {
        this.history = new Stack<>();
        save(EMPTY_VALUE);
    }

    public MyStringBuilder(char[] value) {
        this.history = new Stack<>();
        save(EMPTY_VALUE);
        save(value);
    }

    public MyStringBuilder append(char symbol) {
        char[] currentValue = getCurrentValue();
        int currentValueLength = currentValue.length;
        int newSize = currentValueLength + 1;

        char[] newValue = new char[newSize];
        System.arraycopy(currentValue, 0, newValue, 0, currentValueLength);

        newValue[newSize - 1] = symbol;
        save(newValue);

        return this;
    }

    private void save(char[] newValue) {
        Memento snapshot = new Memento(newValue);
        history.add(snapshot);
    }

    public int getSize() {
        char[] currentValue = getCurrentValue();

        return currentValue.length;
    }

    @Override
    public String toString() {
        char[] currentValue = getCurrentValue();

        return new String(currentValue);
    }

    public void undo() {
        history.pop();
    }

    private char[] getCurrentValue() {
        if (history.empty()) {
            throw new RuntimeException("History is empty");
        }

        Memento lastMemento = history.peek();
        return lastMemento.getState();
    }
}
