package com.learning.spring.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.learning.spring.dao.AdminDAO;
import com.learning.spring.security.dao.UserDAO;
import com.learning.spring.security.model.Role;
import com.learning.spring.security.model.User;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class DefaultController {
    private final UserDAO userDAO;
    private final AdminDAO adminDAO;
    private static final Logger LOGGER = Logger.getLogger(DefaultController.class);

    @Autowired
    public DefaultController(UserDAO userDAO, AdminDAO adminDAO) {
        this.userDAO = userDAO;
        this.adminDAO = adminDAO;
    }

    @GetMapping("/index")
    @PreAuthorize(value = "hasAnyAuthority('users:read','users:write','users:check')")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userDAO.findByUsername(currentPrincipalName);
        ModelAndView mav=null;
        if (user.getRole().equals(Role.STUDENT)) {
            LOGGER.debug("student logged in");
            LOGGER.debug(user);
            LOGGER.debug("redirecting to /students/id");

            //return "/hello/students";
        } else if (user.getRole().equals(Role.TEACHER)) {
            //model.addAttribute("admin", adminDAO.getAdminById(user.getId()));
            LOGGER.debug("admin logged in");
            LOGGER.debug(user);

           // return "/hello/teachers";
        } else {
            model.addAttribute("admin", adminDAO.showAllInfo(user.getId()));
           // mav = new ModelAndView("hello/admins");
           // mav.addObject("admin", adminDAO.showAllInfo(user.getId()));
            LOGGER.debug("admin logged in");
            LOGGER.debug(user);

           // return "hello/admins";
        }

        return "hello/admins";
    }

}
