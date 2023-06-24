package org.gfg.collection;

import java.util.Objects;

public class Batch {
    private String batchNo;
    private String mentor;

    public Batch(String batchNo, String mentor) {
        this.batchNo = batchNo;
        this.mentor = mentor;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "batchNo='" + batchNo + '\'' +
                ", mentor='" + mentor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Batch batch = (Batch) o;
        return Objects.equals(batchNo, batch.batchNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(batchNo);
    }
}
