package com.learning.spring.controllers;

import com.learning.spring.dao.AdminDAO;
import com.learning.spring.dao.ReviewDAO;
import com.learning.spring.models.Admin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operation/admin")
public class AdminController {

    private final AdminDAO adminDAO;

    private final Logger LOGGER = Logger.getLogger(ReviewDAO.class);

    @Autowired
    public AdminController(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }


    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithAdmin(Model model) {
        model.addAttribute("admin", adminDAO.showAll());
        model.addAttribute("newAdmin", new Admin());

        LOGGER.debug("Show all Admin");

        return "students/operationsOnStudent";
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String showAdminIndex(@PathVariable("id") int id, Model model) {
        model.addAttribute("admin", adminDAO.showAllInfo(id));
        LOGGER.debug("Show Admin with " + id);

        return "students/showInfo";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editAdmin(@PathVariable("id") int id, Model model) {
        Admin admin = adminDAO.showAllInfo(id);
        model.addAttribute("admin", admin);
        LOGGER.debug("Show Admin  " + admin.toString());

        return "students/editStudent";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewAdmin(@ModelAttribute("admin") Admin admin) {

        adminDAO.save(admin);

        LOGGER.debug("Save new Admin" + admin.toString());


        return "redirect:/operation/connectingStudent";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateAdmin(@ModelAttribute("admin") Admin admin, @PathVariable("id") int id) {
        LOGGER.debug("Update Admin" + admin.toString());

        adminDAO.update(id, admin);

        return "redirect:/operation/connectingStudent";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteAdmin(@PathVariable("id") int id) {
        LOGGER.debug("Delete Admin N" + id);

        adminDAO.delete(id);

        return "redirect:/operation/connectingStudent";
    }
}
