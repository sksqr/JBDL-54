package org.gfg;

import com.gfg.Lecture;

public class ChildLecture extends Lecture {

    public ChildLecture() {
        super();
    }

    public ChildLecture(String title, String mentor, String status) {
        super(title, mentor, status);
    }

    @Override
    public void setStatus(String status) {
        super.status = status;
    }
}
