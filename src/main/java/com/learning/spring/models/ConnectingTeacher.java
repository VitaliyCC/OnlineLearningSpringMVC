package com.learning.spring.models;

public class ConnectingTeacher {
    private static Integer cuntConnecting=0;
    private Integer teacherId;
    private Integer subjectId;

    public ConnectingTeacher() {
    }

    public ConnectingTeacher(Integer teacherId, Integer subjectId) {
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public static Integer getCuntConnecting() {
        return cuntConnecting;
    }

    public static void setCuntConnecting(Integer cuntConnecting) {
        ConnectingTeacher.cuntConnecting = cuntConnecting;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
