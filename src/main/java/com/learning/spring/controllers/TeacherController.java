package com.learning.spring.controllers;

import com.learning.spring.dao.ReviewDAO;
import com.learning.spring.dao.TeacherDAO;
import com.learning.spring.models.Task;
import com.learning.spring.models.Teacher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operation/techer")
public class TeacherController {

    private final TeacherDAO teacherDAO;

    private final Logger LOGGER = Logger.getLogger(ReviewDAO.class);

    @Autowired
    public TeacherController(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }


    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithTeacher(Model model) {
        model.addAttribute("teacher", teacherDAO.showAll());
        model.addAttribute("newTeacher", new Teacher());

        LOGGER.debug("Show all Teacher");

        return "students/operationsOnStudent";
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String showTeacherIndex(@PathVariable("id") int id, Model model) {
        model.addAttribute("teacher", teacherDAO.showAllInfo(id));
        LOGGER.debug("Show Teacher with " + id);

        return "students/showInfo";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editTeacher(@PathVariable("id") int id, Model model) {
        Teacher teacher = teacherDAO.showAllInfo(id);
        model.addAttribute("teacher", teacher);
        LOGGER.debug("Show Teacher  " + teacher.toString());

        return "students/editStudent";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewTeacher(@ModelAttribute("subject") Teacher teacher) {

        teacher.save(teacher);

        LOGGER.debug("Save new Teacher" + teacher.toString());


        return "redirect:/operation/connectingStudent";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateTeacher(@ModelAttribute("subject") Teacher teacher, @PathVariable("id") int id) {
        LOGGER.debug("Update Teacher" + teacher.toString());

        teacherDAO.update(id, teacher);

        return "redirect:/operation/connectingStudent";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteTeacher(@PathVariable("id") int id) {
        LOGGER.debug("Delete Teacher N" + id);

        teacherDAO.delete(id);

        return "redirect:/operation/connectingStudent";
    }
}
