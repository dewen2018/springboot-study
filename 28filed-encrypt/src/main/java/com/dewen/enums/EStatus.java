package com.dewen.enums;

public enum EStatus {

    disable("0"), enable("1"), deleted("2"),
    init("10"), start("11"), wait("12"), end("13");

    private final String value;

    EStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static EStatus get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }

    public static EStatus get(String str) {
        for (EStatus e : values()) {
            if (e.toString().equals(str)) {
                return e;
            }
        }
        return null;
    }

}