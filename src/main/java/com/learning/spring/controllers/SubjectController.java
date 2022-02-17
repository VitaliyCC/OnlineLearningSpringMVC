package com.learning.spring.controllers;

import com.learning.spring.dao.ReviewDAO;
import com.learning.spring.dao.SubjectDAO;
import com.learning.spring.models.Review;
import com.learning.spring.models.Subject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/operation/subject")
public class SubjectController {

    private final SubjectDAO subjectDAO;

    private final Logger LOGGER = Logger.getLogger(SubjectController.class);

    @Autowired
    public SubjectController(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithSubject(Model model) {
        model.addAttribute("subjects", subjectDAO.showAll());

        LOGGER.debug("Show all Subject");

        return "subjects/operationsOnSubject";
    }

    @GetMapping("/show")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String showSubjectIndex(@RequestParam("id") int id, Model model) {
        Subject subject = subjectDAO.showAllInfo(id);
        model.addAttribute("subject", subject);

        LOGGER.debug("Show Subject with " + id);

        return "subjects/showInfo";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editSubject(@RequestParam("id") int id, Model model) {
        Subject subject = subjectDAO.showAllInfo(id);
        model.addAttribute("subject", subject);
        LOGGER.debug("Show Subject  " + subject.toString());

        return "subjects/editSubject";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewSubject(@ModelAttribute("subject") Subject subject) {

        try {
            subjectDAO.save(subject);
        } catch (SQLException e) {
            LOGGER.error("Can`t save new Subject " + e);
        }

        LOGGER.debug("Save new Subject" + subject.toString());


        return "redirect:/operation/subject";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateSubject(@ModelAttribute("subject") Subject subject, @RequestParam("id") int id) {
        LOGGER.debug("Update Subject" + subject.toString());

        subjectDAO.update(id, subject);

        return "redirect:/operation/subject";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteSubject(@RequestParam("id") int id) {
        LOGGER.debug("Delete Subject N" + id);

        subjectDAO.delete(id);

        return "redirect:/operation/subject";
    }
}
