package org.gfg.collection;

public class Student implements Comparable {
    private String rollNo;

    private String name;

    private Integer totalMarks;

    public Student(String rollNo, String name, Integer totalMarks) {
        this.rollNo = rollNo;
        this.name = name;
        this.totalMarks = totalMarks;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo='" + rollNo + '\'' +
                ", name='" + name + '\'' +
                ", totalMarks=" + totalMarks +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Student otherStudent = (Student) o;
        return this.rollNo.compareTo(otherStudent.getRollNo());
    }
}
