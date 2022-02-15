package com.learning.spring.controllers;

import com.learning.spring.dao.ReviewDAO;
import com.learning.spring.dao.TeacherDAO;
import com.learning.spring.models.Student;
import com.learning.spring.models.Teacher;
import com.learning.spring.security.dao.UserDAO;
import com.learning.spring.security.model.Role;
import com.learning.spring.security.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/operation/teacher")
public class TeacherController {

    private final TeacherDAO teacherDAO;
    private final Logger LOGGER = Logger.getLogger(ReviewDAO.class);
    private final UserDAO userDAO;

    @Autowired
    public TeacherController(TeacherDAO teacherDAO, UserDAO userDAO) {
        this.teacherDAO = teacherDAO;
        this.userDAO = userDAO;
    }


    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithTeacher(Model model) {
        model.addAttribute("teachers", teacherDAO.showAll());
        model.addAttribute("newTeacher", new Teacher());

        LOGGER.debug("Show all Teacher");

        return "teachers/operationsOnTeacher";
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String showTeacherIndex(@PathVariable("id") int id, Model model) {
        Teacher teacher = teacherDAO.showAllInfo(id);
        model.addAttribute("teacher",teacher);
        model.addAttribute("subjects",teacher.getSubjectList());
        model.addAttribute("reviews",teacher.getReviewList());

        LOGGER.debug("show teacher with " + id);
        LOGGER.debug("show teacher with " + teacher.toString());
        return "teachers/showInfo";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editTeacher(@PathVariable("id") int id, Model model) {
        Teacher teacher = teacherDAO.showAllInfo(id);
        model.addAttribute("teacher", teacher);
        LOGGER.debug("Show Teacher  " + teacher.toString());

        return "teachers/editTeacher";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewTeacher(@ModelAttribute("teacher") Teacher teacher) {

        User user = null;
        try {
            teacherDAO.save(teacher);
            user = new User(teacher.getLogin(), teacher.getPassword(), Role.TEACHER);
            userDAO.save(user);
        } catch (SQLException e) {
            LOGGER.error("Save Teacher"+e);
            e.printStackTrace();
        }

        LOGGER.debug("Save new Teacher" + teacher.toString());
        LOGGER.debug("Save new user" + user.toString());



        return "redirect:/operation/teacher";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateTeacher(@ModelAttribute("teacher") Teacher teacher, @PathVariable("id") int id) {
        Teacher temp = teacherDAO.showAllInfo(id);

        User user = new User();
        user.setId(temp.getId());
        user.setLogin(teacher.getLogin());
        user.setPassword(teacher.getPassword());
        user.setRoleString(teacher.getRole());

        LOGGER.debug("Update teacher" + teacher.toString());
        LOGGER.debug("Update user" + user.toString());

        teacherDAO.update(id, teacher);
        userDAO.update(user);

        return "redirect:/operation/teacher";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteTeacher(@PathVariable("id") int id) {
        int temp = teacherDAO.showAllInfo(id).getId();

        LOGGER.debug("Delete teacher N" + id);
        LOGGER.debug("Delete user N" + id);

        teacherDAO.delete(id);
        userDAO.delete(temp);

        return "redirect:/operation/teacher";
    }
}
