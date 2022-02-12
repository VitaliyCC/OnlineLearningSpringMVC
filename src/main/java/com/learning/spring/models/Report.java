package com.learning.spring.models;

import java.sql.Date;
import java.time.LocalDateTime;

public class Report {
    private static Integer countReportl=0;
    private Integer reportId;
    private String solutions;
    private Date sendTime;
    private Integer studentID;
    private String taskName;

    public Report() {
    }

    public Report(Integer reportId, String solutions, Date sendTime, Integer studentID, String taskName) {
        this.reportId = reportId;
        this.solutions = solutions;
        this.sendTime = sendTime;
        this.studentID = studentID;
        this.taskName = taskName;
    }

    public static Integer getCountReportl() {
        return countReportl;
    }

    public static void setCountReportl(Integer countReportl) {
        Report.countReportl = countReportl;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getSolutions() {
        return solutions;
    }

    public void setSolutions(String solutions) {
        this.solutions = solutions;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

}
