package com.gfg;

public enum LectureStatus {
    UPCOMING("UPCOMING"),LIVE("LIVE"),DONE("DONE");

    private String value;

    LectureStatus(String value) {
        this.value = value;
    }
}
