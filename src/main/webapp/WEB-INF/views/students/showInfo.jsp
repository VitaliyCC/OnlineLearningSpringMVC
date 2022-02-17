<%@ page import="com.learning.spring.models.Student" %>
<%@ page import="com.learning.spring.models.Subject" %>
<%@ page import="java.util.List" %>
<%@ page import="com.learning.spring.models.Report" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Student information</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

<jsp:include page="/WEB-INF/views/templates/nav.jsp"/>

<div class="container mt-3">
    <div class="row">
        <div class="col-sm-3">
            <%Student student = ((Student) request.getAttribute("student"));%>
            <h3>Name:</h3>
            <p><%=student.getName()%> <%=student.getSurname()%> <%=student.getPatronymic()%>
            </p>
            <br>
            <h3>Login information</h3>
            <p>Username - <%=student.getLogin()%>
            </p>
            <p>Role - <%=student.getRole()%>
            </p>
        </div>


        <div class="col-sm-8">
            <h3 class="mt-4">Subjects studied by the student</h3>

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Subject id (click to see full info)</th>
                    <th>Name</th>
                    <th>Semester</th>
                    <th>Max grade</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Subject> list = student.getSubjectList();
                    for (Subject subject : list) {

                %>
                <tr>

                    <th><a class="link-secondary"
                           href="/operation/subject/show?id=<%=subject.getSubjectID()%>"
                    ><%= subject.getSubjectID()%></a>
                    </th>
                    <th><%=subject.getSubjectName()%>
                    </th>
                    <th><%=subject.getSemester()%>
                    </th>
                    <th><%=subject.getMaxGrade()%>
                    </th>
                </tr>
                <%}%>
                </tbody>
            </table>

        </div>
    </div>
</div>
<div class="container mt-5">
    <h2>Student`s reports</h2>
    <div class="container">
        <p></p>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Report id (click to see full info)</th>
                <th>Task name</th>
                <th>Solutions</th>
                <th>Send time</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Report> reportList = student.getReportList();
                for (Report report : reportList) {

            %>
            <tr>
                <th><a class="link-secondary"
                       href="/operation/report/show?id=<%=report.getReportId()%>"
                ><%=report.getReportId()%>
                </a>
                </th>
                <th><%=report.getTaskName()%>></th>
                <th><%=report.getSolutions()%>
                </th>
                <th><%=report.getSendTime()%>
                </th>
                <th>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#new">
                        Show review for this report
                    </button>
                </th>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
</div>

<jsp:include page="/WEB-INF/views/templates/footer.jsp"/>

</body>
</html>


