<%@ page import="com.learning.spring.models.Subject" %>
<%@ page import="java.util.List" %>
<%@ page import="com.learning.spring.models.Task" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Tasks</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navUser.jsp"/>
<div class="container">

    <div class="container">
        <div class="col-sm-7">
            <h2>Search in the table</h2>
            <p>Search for symbols in all columns:</p>
            <input class="form-control" id="myInput" type="text" placeholder="Search..">
            <br>
        </div>

    </div>
    <table class="table table-bordered">
        <thead>
        <%
            Subject subject = ((Subject) request.getAttribute("subject"));
            String url;
            String operation;
            String colomnName="";
            if ((Integer) request.getAttribute("idSt") != -1) {
                url = "/operation/report/show?id=" + (Integer) request.getAttribute("idSt");
                operation = "Create new report";
                colomnName = "Your grade";
            } else {
                url = "/operation/task/show?id=" + (Integer) request.getAttribute("idT");
                operation = "Swow reports";
            }%>
        <tr>
            <th>Task name</th>
            <th>Subject id</th>
            <th>Task</th>
            <th>Max grade</th>
            <th><%=colomnName%></th>
        </tr>
        </thead>
        <tbody id="myTable">
        <%
            for (Task task : subject.getTaskList()) {
                if ((Integer) request.getAttribute("idSt") != -1) {
                    url = "/operation/report/show?id=" + (Integer) request.getAttribute("idSt");
                    operation = "Create new report";
                } else {
                    url = "/operation/task/show?id=" + (Integer) request.getAttribute("idT");
                    operation = "Swow reports";
                }
                url += "&nameT=" + String.valueOf(task.getTaskName());
        %>
        <tr>
            <th><%=task.getTaskName()%>
            </th>
            <th><a class="link-secondary" href="/operation/subject/show?id=<%=subject.getSubjectID()%>"
            ><%=task.getSubjectId()%>
            </a>
            </th>
            <th><%=task.getSubject()%>
            </th>
            <th><%=task.getMaxGrade()%>
            </th>
            <th><%=task.getGrade()%>
            </th>
            <th><a class="link-secondary" href="<%=url%>"
            ><%=operation%>
            </a>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>

<jsp:include page="/WEB-INF/views/templates/footer.jsp"/>

<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });

</script>
<script>
    // Disable form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Get the forms we want to add validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>

