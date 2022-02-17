package com.learning.spring.controllers;

import com.learning.spring.dao.AdminDAO;
import com.learning.spring.dao.ReviewDAO;
import com.learning.spring.models.Admin;
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
@RequestMapping("/operation/admin")
public class AdminController {

    private final AdminDAO adminDAO;
    private final UserDAO userDAO;

    private final Logger LOGGER = Logger.getLogger(AdminController.class);

    @Autowired
    public AdminController(AdminDAO adminDAO, UserDAO userDAO) {
        this.adminDAO = adminDAO;
        this.userDAO = userDAO;
    }


    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithAdmin(Model model) {
        model.addAttribute("admins", adminDAO.showAll());
        model.addAttribute("newAdmin", new Admin());

        LOGGER.debug("Show all Admin");

        return "admins/operationsAdmin";
    }

    /*@GetMapping("/show/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String showAdminIndex(@PathVariable("id") int id, Model model) {
        model.addAttribute("admins", adminDAO.showAllInfo(id));
        LOGGER.debug("Show Admin with " + id);

        return "admins/showInfo";
    }*/

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editAdmin(@PathVariable("id") int id, Model model) {
        Admin admin = adminDAO.showAllInfo(id);
        LOGGER.debug("Show Admin  " + admin.toString());
        model.addAttribute("admin", admin);

        return "admins/editAdmin";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewAdmin(@ModelAttribute("newAdmin") Admin admin) {
        User user = null;

        LOGGER.debug("Save new Admin" + admin.toString());
        try {
            user = new User(admin.getLogin(), admin.getPassword(), Role.ADMIN);
            LOGGER.debug("Save new гыук" + user.toString());
            adminDAO.save(admin);
            userDAO.save(user);
        } catch (SQLException e) {
            LOGGER.error(e);
        }



        return "redirect:/operation/admin";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateAdmin(@ModelAttribute("admin") Admin admin, @PathVariable("id") int id) {
        LOGGER.debug("Update Admin" + admin.toString());

        adminDAO.update(id, admin);

        return "redirect:/operation/admin";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteAdmin(@PathVariable("id") int id) {
        LOGGER.debug("Delete Admin N" + id);

        adminDAO.delete(id);

        return "redirect:/operation/admin";
    }
}
