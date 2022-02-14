package com.learning.spring.controllers;

import com.learning.spring.dao.ConnectingStudentDAO;
import com.learning.spring.dao.ConnectingTeacherDAO;
import com.learning.spring.models.ConnectingStudent;
import com.learning.spring.models.ConnectingTeacher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@Controller
@RequestMapping("/operation/connectingPerson")
public class ConnectingController {

    private final ConnectingStudentDAO connectingStudentDAO;
    private final ConnectingTeacherDAO connectingTeacherDAO;

    private final Logger LOGGER = Logger.getLogger(ConnectingController.class);

    @Autowired
    public ConnectingController(ConnectingStudentDAO connectingStudentDAO, ConnectingTeacherDAO connectingTeacherDAO) {
        this.connectingStudentDAO = connectingStudentDAO;
        this.connectingTeacherDAO = connectingTeacherDAO;
    }


    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithConnectingStudent(Model model) {
        model.addAttribute("connectingS", connectingStudentDAO.showAll());
        model.addAttribute("connectingT", connectingTeacherDAO.showAll());
        model.addAttribute("newConnectingS", new ConnectingStudent());
        model.addAttribute("newConnectingT", new ConnectingTeacher());

        LOGGER.debug("Show all ConnectingStudent");

        return "connectingPerson/operationsOnConnectingPerson";
    }

   /* @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyAuthority('users:check','users:write')")
    public String showConnectingStudentIndex(@PathVariable("id") int id, Model model) {
        model.addAttribute("connectingS", connectingStudentDAO.showAllInfo(id));
        LOGGER.debug("Show ConnectingStudent with " + id);

        return "connectingStudents/showInfo";
    }*/

    @GetMapping("/S/edit/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editConnectingStudentIndex(@PathVariable("id") int id, Model model) {
        ConnectingStudent connectingStudent = connectingStudentDAO.showAllInfo(id);
        ConnectingTeacher connectingTeacher = connectingTeacherDAO.showAllInfo(id);
        model.addAttribute("connectingS", connectingStudent);
        model.addAttribute("connectingT", connectingTeacher);
        LOGGER.debug("Show ConnectingStudent  " + connectingStudent.toString());
        LOGGER.debug("Show ConnectingStudent  " + connectingTeacher.toString());

        return "connectingPerson/editConnectingStudent";
    }
    @GetMapping("/T/edit/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editConnectingTeacherIndex(@PathVariable("id") int id, Model model) {
        ConnectingTeacher connectingTeacher = connectingTeacherDAO.showAllInfo(id);
        model.addAttribute("connectingT", connectingTeacher);
        LOGGER.debug("Show ConnectingTeacher  " + connectingTeacher.toString());

        return "connectingPerson/editConnectingTeacher";
    }

    @PostMapping("/S/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewConnectingStudent(@ModelAttribute("connectingS") ConnectingStudent connectingStudent) {
        System.out.println(connectingStudent.toString());
        try {
            connectingStudentDAO.save(connectingStudent);
        } catch (SQLException e) {
            System.out.println(e);
            LOGGER.error(e);
        }

        LOGGER.debug("Save new ConnectingStudent" + connectingStudent.toString());


        return "redirect:/operation/connectingPerson";
    }
    @PostMapping("/T/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewConnectingTeacher(@ModelAttribute("connectingT") ConnectingTeacher connectingTeacher) {
        System.out.println(connectingTeacher.toString());
        try {
            connectingTeacherDAO.save(connectingTeacher);
        } catch (SQLException e) {
            System.out.println(e);
            LOGGER.error(e);
        }

        LOGGER.debug("Save new ConnectingTeacher" + connectingTeacher.toString());


        return "redirect:/operation/connectingPerson";
    }

    @PatchMapping("/S/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateConnectingStudent(@ModelAttribute("connectingS") ConnectingStudent connectingStudent, @PathVariable("id") int id) {
        LOGGER.debug("Update ConnectingStudent" + connectingStudent.toString());

        connectingStudentDAO.update(id, connectingStudent);

        return "redirect:/operation/connectingPerson";
    }
    @PatchMapping("/T/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateConnectingTeacher(@ModelAttribute("connectingT") ConnectingTeacher connectingTeacher, @PathVariable("id") int id) {
        LOGGER.debug("Update ConnectingStudent" + connectingTeacher.toString());

        connectingTeacherDAO.update(id, connectingTeacher);

        return "redirect:/operation/connectingPerson";
    }
    @DeleteMapping("/S/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteConnectingStudent(@PathVariable("id") int id) {
        LOGGER.debug("Delete ConnectingStudent N" + id);

        connectingStudentDAO.delete(id);

        return "redirect:/operation/connectingPerson";
    }

    @DeleteMapping("/T/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteConnectingTeacher(@PathVariable("id") int id) {
        LOGGER.debug("Delete ConnectingTeacher N" + id);

        connectingTeacherDAO.delete(id);

        return "redirect:/operation/connectingPerson";
    }
}
