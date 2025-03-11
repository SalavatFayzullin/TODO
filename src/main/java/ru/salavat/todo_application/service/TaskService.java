package ru.salavat.todo_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.salavat.todo_application.model.Task;
import ru.salavat.todo_application.repo.TaskRepository;

import java.util.List;

// TaskService.java
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByCompletion(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }
}