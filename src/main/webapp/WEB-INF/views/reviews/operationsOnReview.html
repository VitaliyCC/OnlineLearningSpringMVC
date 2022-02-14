<!DOCTYPE html>
<html lang="en" xmlns:th="http://thymeleaf.org">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div th:insert="templates/nav :: copy"></div>
<br>
<br>
<br>
<div class="container">
    <h3>Subjects</h3>
    <div class="container">
        <p></p>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Review id (click to see full info)</th>
                <th>Teacher id</th>
                <th>Report id</th>
                <th>Grade</th>
                <th>Time review</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="review : ${reviews}">
                <!--<th scope="row">1</th>-->
                <th><a class="link-secondary"
                       th:href="@{/operation/review/show/{id}(id=${review.getReviewId()})}"
                       th:text="${review.getReviewId()}">ID</a>
                </th>
                <th><a class="link-secondary"
                       th:href="@{/operation/teacher/show/{id}(id=${review.getTeacherId()})}"
                       th:text="${review.getTeacherId()}">ID</a>
                </th>
                <th><a class="link-secondary"
                       th:href="@{/operation/report/show/{id}(id=${review.getReportId()})}"
                       th:text="${review.getReportId()}">ID</a>
                </th>
                <th th:text="${review.getGrade()}">NAME</th>
                <th th:text="${review.getTimeReview()}">NAME</th>

                <th><a class="link-secondary" th:href="@{/operation/review/edit/{id}(id=${review.getReviewId()})}"
                >Edit</a>
                </th>
            </tr>
            </tbody>
        </table>
    </div>
    <br>
    <br>
    <br>
    <h2>Operation on subjects</h2>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#new">
        Add new subject
    </button>

    <!-- The Modal -->
    <div class="modal" id="new">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Modal Heading</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form th:method="POST" th:action="@{/operation/review/add}" th:object="${newReview}"
                          class="was-validated">
                        <div class="form-group">
                            <label for="teacherId" class="form-label">Enter your teacher id: </label>
                            <input type="number" th:field="*{teacherId}" id="teacherId" class="form-control" required
                                   minlength="2"
                                   maxlength="15"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                        <div class="form-group">
                            <label for="semester" class="form-label">Enter academic semester: </label>
                            <input type="number" th:field="*{semester}" id="semester" class="form-control" required
                                   aria-valuemin="1" aria-valuemax="8"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                        <div class="form-group">
                            <label for="maxGrade" class="form-label">Enter maximum grade for the subject : </label>
                            <input type="number" th:field="*{maxGrade}" id="maxGrade" class="form-control" required
                                   aria-valuemin="0" aria-valuemax="200"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
