package com.learning.spring.controllers;

import com.learning.spring.dao.ReviewDAO;
import com.learning.spring.dao.TaskDAO;
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

    private final Logger LOGGER = Logger.getLogger(ReviewDAO.class);

    @Autowired
    public TaskController(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }


    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithTask(Model model) {
        model.addAttribute("task", taskDAO.showAll());
        model.addAttribute("newTask", new Task());

        LOGGER.debug("Show all Task");

        return "students/operationsOnStudent";
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String showTaskIndex(@PathVariable("id") int id, Model model) {
        model.addAttribute("task", taskDAO.showAllInfo(id));
        LOGGER.debug("Show Task with " + id);

        return "students/showInfo";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editTask(@PathVariable("id") int id, Model model) {
        Task task = taskDAO.showAllInfo(id);
        model.addAttribute("task", task);
        LOGGER.debug("Show Task  " + task.toString());

        return "students/editStudent";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewTask(@ModelAttribute("task") Task task) {

        taskDAO.save(task);

        LOGGER.debug("Save new Task" + task.toString());


        return "redirect:/operation/connectingStudent";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateTask(@ModelAttribute("task") Task task, @PathVariable("id") int id) {
        LOGGER.debug("Update Task" + task.toString());

        taskDAO.update(id, task);

        return "redirect:/operation/connectingStudent";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteTask(@PathVariable("id") int id) {
        LOGGER.debug("Delete Task N" + id);

        taskDAO.delete(id);

        return "redirect:/operation/connectingStudent";
    }
}
