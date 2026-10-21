package com.zaicev;

public final class Memento {

    private final char[] state;

    public Memento(char[] state) {
        this.state = state;
    }

    public char[] getState() {
        return this.state;
    }
}
