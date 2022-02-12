package com.learning.spring.models;

public class ConnectingStudent {
    private static Integer cuntConnecting=0;
    private Integer studentId;
    private Integer subjectId;

    public ConnectingStudent() {
    }

    public ConnectingStudent(Integer studentId, Integer subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public static Integer getCuntConnecting() {
        return cuntConnecting;
    }

    public static void setCuntConnecting(Integer cuntConnecting) {
        ConnectingStudent.cuntConnecting = cuntConnecting;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
