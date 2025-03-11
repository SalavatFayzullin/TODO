package ru.salavat.todo_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.salavat.todo_application.model.Task;
import ru.salavat.todo_application.service.TaskService;

@Controller
@RequestMapping("/tasks")
public class WebTaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "edit-task";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        taskService.updateTask(id, task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/toggle/{id}")
    public String toggleTaskCompletion(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        task.setCompleted(!task.isCompleted());
        taskService.updateTask(id, task);
        return "redirect:/tasks";
    }
}