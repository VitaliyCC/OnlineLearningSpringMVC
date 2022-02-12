package com.learning.spring.models;

public class Task {
    private static Integer countSubject=0;
    private String taskName;
    private Integer subjectId;
    private String subject;
    private Integer maxGrade;

    public Task() {
    }

    public Task(String taskName, Integer subjectId, String subject, Integer maxGrade) {
        this.taskName = taskName;
        this.subjectId = subjectId;
        this.subject = subject;
        this.maxGrade = maxGrade;
    }

    public static Integer getCountSubject() {
        return countSubject;
    }

    public static void setCountSubject(Integer countSubject) {
        Task.countSubject = countSubject;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(Integer maxGrade) {
        this.maxGrade = maxGrade;
    }
}
