package com.example.sara.loginregistera;

public enum Size {
    S("S"),
    M("M"),
    L("L"),
    XL("XL");

    private final String value;

    Size(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
