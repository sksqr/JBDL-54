package com.gfg;

import java.util.Date;

public class Lecture {

    public static int num =0;

    public static String company = "GFG"; //new String("GFG");
    public String title;
    String mentor;
    protected String status;

    private Date created;

    public Lecture(String title, String mentor, String status) {
        this.title = title;
        this.mentor = mentor;
        this.status = status;
        created = new Date();
        num++;
    }

    public Lecture() {
        num++;
        created = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "title='" + title + '\'' +
                ", mentor='" + mentor + '\'' +
                ", status='" + status + '\'' +
                ", created=" + created +
                '}';
    }
}
