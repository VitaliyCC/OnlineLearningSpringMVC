package com.learning.spring.controllers;

import com.learning.spring.models.Student;
import com.learning.spring.security.dao.UserDAO;
import com.learning.spring.security.model.Role;
import com.learning.spring.security.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.learning.spring.dao.StudentDAO;

import java.sql.SQLException;

@Controller
@RequestMapping("/operation/student")
public class StudentController {

    private final StudentDAO studentDAO;
    private final UserDAO userDAO;
    private final Logger LOGGER = Logger.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentDAO studentDAO, UserDAO userDAO) {
        this.studentDAO = studentDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithStudent(Model model) {
        model.addAttribute("students", studentDAO.showAll());
        model.addAttribute("newStudent", new Student());
        LOGGER.debug("show all students");
        return "students/operationsOnStudent";
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyAuthority('users:check','users:write')")
    public String showStudentIndex(@PathVariable("id") int id, Model model) {
        Student student = studentDAO.showAllInfo(id);
        model.addAttribute("student",student);
        model.addAttribute("subjects",student.getSubjectList());
        model.addAttribute("reports",student.getReportList());
        model.addAttribute("reviews",student.getReviewMap());
        LOGGER.debug("show student with " + id);

        return "students/showInfo";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editStudentIndex(@PathVariable("id") int id, Model model) {
        Student student = studentDAO.showAllInfo(id);
        model.addAttribute("student", student);
        LOGGER.debug("Show student  " + student.toString());

        return "students/editStudent";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewStudent(@ModelAttribute("student") Student student) {
        User user = null;
        try {
            studentDAO.save(student);
            user = new User(student.getLogin(), student.getPassword(), Role.STUDENT);
            userDAO.save(user);
        } catch (SQLException e) {
            LOGGER.error("Save Student");
            e.printStackTrace();
        }

        LOGGER.debug("Save new Student" + student.toString());
        LOGGER.debug("Save new user" + user.toString());

        return "redirect:/operation/student";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateStudent(@ModelAttribute("student") Student student, @PathVariable("id") int id) {
        Student temp = studentDAO.showAllInfo(id);

        User user = new User();
        user.setId(temp.getId());
        user.setLogin(student.getLogin());
        user.setPassword(student.getPassword());
        user.setRoleString(student.getRole());

        LOGGER.debug("Update student" + student.toString());
        LOGGER.debug("Update user" + user.toString());

        studentDAO.update(id, student);
        userDAO.update(user);

        return "redirect:/operation/student";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteStudent(@PathVariable("id") int id) {
        int temp = studentDAO.showAllInfo(id).getId();

        LOGGER.debug("Delete student N" + id);
        LOGGER.debug("Delete user N" + id);

        studentDAO.delete(id);
        userDAO.delete(temp);

        return "redirect:/operation/student";
    }
}
