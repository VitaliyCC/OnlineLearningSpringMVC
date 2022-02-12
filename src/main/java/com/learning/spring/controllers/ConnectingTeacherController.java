package com.learning.spring.controllers;

import com.learning.spring.dao.ConnectingTeacherDAO;
import com.learning.spring.models.ConnectingStudent;
import com.learning.spring.models.ConnectingTeacher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operation/connectingTeacher")
public class ConnectingTeacherController {

    private final ConnectingTeacherDAO connectingTeacherDAO;

    private final Logger LOGGER = Logger.getLogger(ConnectingTeacherController.class);
    @Autowired
    public ConnectingTeacherController(ConnectingTeacherDAO connectingTeacherDAO) {
        this.connectingTeacherDAO = connectingTeacherDAO;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithConnectingTeacher(Model model) {
        model.addAttribute("connectingT", connectingTeacherDAO.showAll());
        model.addAttribute("newConnectingT", new ConnectingTeacher());

        LOGGER.debug("Show all ConnectingTeacher");

        return "students/operationsOnStudent";
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String showConnectingTeacherIndex(@PathVariable("id") int id, Model model) {
        model.addAttribute("connectingT", connectingTeacherDAO.showAllInfo(id));
        LOGGER.debug("Show ConnectingTeacher with " + id);

        return "students/showInfo";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editConnectingTeacherIndex(@PathVariable("id") int id, Model model) {
        ConnectingTeacher connectingTeacher = connectingTeacherDAO.showAllInfo(id);
        model.addAttribute("connectingT", connectingTeacher);
        LOGGER.debug("Show ConnectingTeacher  " + connectingTeacher.toString());

        return "students/editStudent";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewConnectingTeacher(@ModelAttribute("connectingT") ConnectingTeacher connectingTeacher) {

        connectingTeacherDAO.save(connectingTeacher);

        LOGGER.debug("Save new ConnectingTeacher" + connectingTeacher.toString());


        return "redirect:/operation/connectingStudent";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateConnectingTeacher(@ModelAttribute("connectingT") ConnectingTeacher connectingTeacher, @PathVariable("id") int id) {
        LOGGER.debug("Update ConnectingTeacher" + connectingTeacherDAO.toString());

        connectingTeacherDAO.update(id, connectingTeacher);

        return "redirect:/operation/connectingStudent";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteConnectingTeacher(@PathVariable("id") int id) {
        LOGGER.debug("Delete ConnectingTeacher N" + id);

        connectingTeacherDAO.delete(id);

        return "redirect:/operation/connectingStudent";
    }
}
