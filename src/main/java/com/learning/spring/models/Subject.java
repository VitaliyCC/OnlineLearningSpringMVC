package com.learning.spring.models;

public class Subject {
    private static Integer countSubjects = 0;
    private Integer subjectID;
    private String subjectName;
    private Integer semester;
    private Integer maxGrade;

    public Subject() {
    }

    public Subject(Integer subjectID, String subjectName, Integer semester, Integer maxGrade) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.semester = semester;
        this.maxGrade = maxGrade;
    }

    public static Integer getCountSubjects() {
        return countSubjects;
    }

    public static void setCountSubjects(Integer countSubjects) {
        Subject.countSubjects = countSubjects;
    }

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(Integer maxGrade) {
        this.maxGrade = maxGrade;
    }
}
