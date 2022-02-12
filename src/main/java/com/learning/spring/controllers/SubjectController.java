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

@Controller
@RequestMapping("/operation/subject")
public class SubjectController {

    private final SubjectDAO subjectDAO;

    private final Logger LOGGER = Logger.getLogger(ReviewDAO.class);

    @Autowired
    public SubjectController(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithSubject(Model model) {
        model.addAttribute("subject", subjectDAO.showAll());
        model.addAttribute("newSubject", new Subject());

        LOGGER.debug("Show all Subject");

        return "students/operationsOnStudent";
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String showSubjectIndex(@PathVariable("id") int id, Model model) {
        model.addAttribute("subject", subjectDAO.showAllInfo(id));
        LOGGER.debug("Show Subject with " + id);

        return "students/showInfo";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editSubject(@PathVariable("id") int id, Model model) {
        Subject subject = subjectDAO.showAllInfo(id);
        model.addAttribute("subject", subject);
        LOGGER.debug("Show Subject  " + subject.toString());

        return "students/editStudent";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewSubject(@ModelAttribute("subject") Subject subject) {

        subjectDAO.save(subject);

        LOGGER.debug("Save new Subject" + subject.toString());


        return "redirect:/operation/connectingStudent";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateSubject(@ModelAttribute("subject") Subject subject, @PathVariable("id") int id) {
        LOGGER.debug("Update Subject" + subject.toString());

        subjectDAO.update(id, subject);

        return "redirect:/operation/connectingStudent";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteSubject(@PathVariable("id") int id) {
        LOGGER.debug("Delete Subject N" + id);

        subjectDAO.delete(id);

        return "redirect:/operation/connectingStudent";
    }
}
