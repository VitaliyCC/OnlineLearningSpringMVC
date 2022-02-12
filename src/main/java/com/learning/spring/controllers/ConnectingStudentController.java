package com.learning.spring.controllers;

import com.learning.spring.dao.ConnectingStudentDAO;
import com.learning.spring.models.ConnectingStudent;
import com.learning.spring.models.Student;
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
@RequestMapping("/operation/connectingStudent")
public class ConnectingStudentController {

    private final ConnectingStudentDAO connectingStudentDAO;

    private final Logger LOGGER = Logger.getLogger(ConnectingStudentController.class);

    @Autowired
    public ConnectingStudentController(ConnectingStudentDAO connectingStudentDAO) {
        this.connectingStudentDAO = connectingStudentDAO;
    }


    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithConnectingStudent(Model model) {
        model.addAttribute("connectingS", connectingStudentDAO.showAll());
        model.addAttribute("newConnectingS", new ConnectingStudent());

        LOGGER.debug("Show all ConnectingStudent");

        return "students/operationsOnStudent";
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyAuthority('users:check','users:write')")
    public String showConnectingStudentIndex(@PathVariable("id") int id, Model model) {
        model.addAttribute("connectingS", connectingStudentDAO.showAllInfo(id));
        LOGGER.debug("Show ConnectingStudent with " + id);

        return "students/showInfo";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editConnectingStudentIndex(@PathVariable("id") int id, Model model) {
        ConnectingStudent connectingStudent = connectingStudentDAO.showAllInfo(id);
        model.addAttribute("ConnectingStudent", connectingStudent);
        LOGGER.debug("Show ConnectingStudent  " + connectingStudent.toString());

        return "students/editStudent";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewConnectingStudent(@ModelAttribute("connectingS") ConnectingStudent connectingStudent) {

        connectingStudentDAO.save(connectingStudent);

        LOGGER.debug("Save new ConnectingStudent" + connectingStudent.toString());


        return "redirect:/operation/connectingStudent";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateConnectingStudent(@ModelAttribute("connectingS") ConnectingStudent connectingStudent, @PathVariable("id") int id) {
        LOGGER.debug("Update ConnectingStudent" + connectingStudent.toString());

        connectingStudentDAO.update(id, connectingStudent);

        return "redirect:/operation/connectingStudent";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteConnectingStudent(@PathVariable("id") int id) {
        LOGGER.debug("Delete ConnectingStudent N" + id);

        connectingStudentDAO.delete(id);

        return "redirect:/operation/connectingStudent";
    }
}
