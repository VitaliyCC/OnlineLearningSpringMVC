package com.learning.spring.controllers;

import com.learning.spring.dao.ReportDAO;
import com.learning.spring.dao.ReviewDAO;
import com.learning.spring.dao.SubjectDAO;
import com.learning.spring.dao.TaskDAO;
import com.learning.spring.models.Report;
import com.learning.spring.models.Subject;
import com.learning.spring.models.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operation/task")
public class TaskController {

    private final TaskDAO taskDAO;
    private final SubjectDAO subjectDAO;
    private final ReportDAO reportDAO;

    private final Logger LOGGER = Logger.getLogger(TaskController.class);

    @Autowired
    public TaskController(TaskDAO taskDAO, SubjectDAO subjectDAO, ReportDAO reportDAO) {
        this.taskDAO = taskDAO;
        this.subjectDAO = subjectDAO;
        this.reportDAO = reportDAO;
    }


    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:read','users:check')")
    public String workingWithTask(Model model,
                                  @RequestParam("idS") Integer idS,
                                  @RequestParam("idSt") Integer idSt,
                                  @RequestParam("idT") Integer idT) {
        Subject subject = subjectDAO.showAllInfo(idS);

        if (idSt != -1) {
            for (Task task : subject.getTaskList()) {
                task.setGrade(taskDAO.calculationSubjectProgress(idSt, task.getTaskName()));
            }
        }

        model.addAttribute("subject", subject);
        model.addAttribute("idSt", idSt);
        model.addAttribute("idT", idT);

        LOGGER.debug("Show all Task for subject " + subject.toString());

        return "tasks/subject`sTasks";
    }

    @GetMapping("/show")
    @PreAuthorize("hasAnyAuthority('users:write','users:check')")
    public String showTaskIndex(@RequestParam("id") int id,
                                @RequestParam("nameT") String name, Model model) {
        Task task = taskDAO.showAllInfo(name);

        for (Report report : task.getReportList()) {
            report.setChecked(reportDAO.isCheckedReport(report.getReportId()));
        }

        model.addAttribute("task", task);
        model.addAttribute("idT", id);

        LOGGER.debug("Show task with " + id);

        return "tasks/showInfo";
    }
}
