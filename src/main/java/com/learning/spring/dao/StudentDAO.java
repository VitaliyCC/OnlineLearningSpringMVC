package com.learning.spring.dao;

import com.learning.spring.models.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.learning.spring.security.model.User;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Component
public class StudentDAO {

    private final Logger LOGGER = Logger.getLogger(StudentDAO.class);

    public StudentDAO() {
    }

    public List<Student> showAll() {
        List<Student> studentList = new LinkedList<>();

        try (
                Connection connection = JDBC.getInstance().getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.execute("SELECT * from STUDENTs NATURAL JOIN Iogin_info");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                studentList.add(parseStudent(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return studentList;
    }


    public Boolean save(Student student) throws SQLException {

        Connection connection = JDBC.getInstance().getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC) VALUES (?, ?, ?, ?, ?)");

        Statement statementForStudent = connection.createStatement();
        Statement statementForUser = connection.createStatement();

        ResultSet resultSet = statementForStudent.executeQuery("SELECT MAX(STUDENT_ID) FROM STUDENTS");
        resultSet.next();
        Student.setCountStudents(resultSet.getInt(1) + 1);

        resultSet = statementForUser.executeQuery("SELECT MAX(ID) FROM Iogin_info");
        resultSet.next();
        User.setCountUsers(resultSet.getInt(1) + 1);

        preparedStatement.setInt(1, Student.getCountStudents());
        preparedStatement.setInt(2, User.getCountUsers());
        preparedStatement.setString(3, student.getSurname());
        preparedStatement.setString(4, student.getName());
        preparedStatement.setString(5, student.getPatronymic());
        preparedStatement.executeUpdate();


        return true;
    }


    public Boolean update(int id, Student student) {
        try (Connection connection = JDBC.getInstance().getConnection();

             PreparedStatement preparedStatementStudent =
                     connection.prepareStatement("UPDATE Students set   SURNAME=?, NAME=?, PATRONYMIC=? where student_id =?");
        ) {
            preparedStatementStudent.setString(1, student.getSurname());
            preparedStatementStudent.setString(2, student.getName());
            preparedStatementStudent.setString(3, student.getPatronymic());
            preparedStatementStudent.setInt(4, id);

            preparedStatementStudent.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
        }
        return true;
    }


    public Boolean delete(Integer id) {
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatementStudent =
                     connection.prepareStatement("delete from Students where student_id =?");
        ) {

            preparedStatementStudent.setInt(1, id);
            preparedStatementStudent.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
        }
        return true;
    }


    public Student showAllInfo(Integer id) {
        Student student = null;
        try (Connection connection = JDBC.getInstance().getConnection();

             PreparedStatement preparedStatementStudent =
                     connection.prepareStatement("SELECT * from STUDENTs NATURAL JOIN Iogin_info Where student_id=?");
             PreparedStatement preparedStatementReports =
                     connection.prepareStatement("select * from Report where student_id = ?");
             PreparedStatement preparedStatementSubjects =
                     connection.prepareStatement("select SB.subject_id, SB.subject_name, SB.semester, SB.max_grade " +
                             "from Subject SB join CONNECTING_STUDENT CS on SB.subject_id = CS.SUBJECT_ID " +
                             "join STUDENTS S on CS.student_id = S.STUDENT_ID where S.student_id=?");
        ) {
            preparedStatementStudent.setInt(1, id);
            ResultSet resultSet = preparedStatementStudent.executeQuery();

            resultSet.next();
            student = parseStudent(resultSet);

            preparedStatementReports.setInt(1, student.getStudentId());

            resultSet = preparedStatementReports.executeQuery();
            student = parseReport(resultSet, student);
            student = parseReview(student);

            preparedStatementSubjects.setInt(1, student.getStudentId());

            resultSet = preparedStatementSubjects.executeQuery();
            student = parseSubjects(resultSet, student);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
        }
        return student;
    }

    public static Student parseReport(ResultSet resultSetReport, Student student) {
        List<Report> reportList = new LinkedList();

        try {
            while (resultSetReport.next()) {

                reportList.add(new Report(
                        resultSetReport.getInt(1),
                        resultSetReport.getString(2),
                        resultSetReport.getDate(3),
                        resultSetReport.getInt(4),
                        resultSetReport.getString(5)));
            }

        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        student.setReportList(reportList);

        return student;
    }

    public static Student parseReview(Student student) {
        HashMap<Integer, List<Review>> map = new HashMap<>();
        List<Review> reviewList = new LinkedList<>();

        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatementReviews =
                     connection.prepareStatement("select * from  Review where report_id = ?");) {

            for (Report report : student.getReportList()) {

                preparedStatementReviews.setInt(1, report.getReportId());
                ResultSet resultSet = preparedStatementReviews.executeQuery();

                while (resultSet.next()) {
                    reviewList.add(new Review(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getInt(3),
                            resultSet.getInt(4),
                            resultSet.getDate(5)));
                }

                map.put(report.getReportId(), reviewList);
            }
            student.setReviewMap(map);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public static Student parseSubjects(ResultSet resultSet, Student student) {
        List<Subject> subjectList = new LinkedList();

        try {
            while (resultSet.next()) {
                subjectList.add(new Subject(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        student.setSubjectList(subjectList);

        return student;
    }

    private static Student parseStudent(ResultSet resultSet) {
        Student temp = null;
        try {
            temp = new Student(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
