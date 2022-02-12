package com.learning.spring.controllers;

import com.learning.spring.dao.ReportDAO;
import com.learning.spring.models.ConnectingStudent;
import com.learning.spring.models.ConnectingTeacher;
import com.learning.spring.models.Report;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operation/report")
public class ReportController {

    private final ReportDAO reportDAO;

    private final Logger LOGGER = Logger.getLogger(ReportDAO.class);
    @Autowired
    public ReportController(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithReport(Model model) {
        model.addAttribute("report", reportDAO.showAll());
        model.addAttribute("newReport", new Report());

        LOGGER.debug("Show all Report");

        return "students/operationsOnStudent";
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String showReportIndex(@PathVariable("id") int id, Model model) {
        model.addAttribute("report", reportDAO.showAllInfo(id));
        LOGGER.debug("Show Report with " + id);

        return "students/showInfo";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editReport(@PathVariable("id") int id, Model model) {
        Report report = reportDAO.showAllInfo(id);
        model.addAttribute("report", report);
        LOGGER.debug("Show Report  " + report.toString());

        return "students/editStudent";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewReportr(@ModelAttribute("report") Report report) {

        reportDAO.save(report);

        LOGGER.debug("Save new Report" + report.toString());


        return "redirect:/operation/connectingStudent";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateReport(@ModelAttribute("report") Report report, @PathVariable("id") int id) {
        LOGGER.debug("Update Report" + report.toString());

        reportDAO.update(id, report);

        return "redirect:/operation/connectingStudent";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteReport(@PathVariable("id") int id) {
        LOGGER.debug("Delete Report N" + id);

        reportDAO.delete(id);

        return "redirect:/operation/connectingStudent";
    }
}
