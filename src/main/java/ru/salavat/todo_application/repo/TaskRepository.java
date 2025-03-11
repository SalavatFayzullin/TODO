package ru.salavat.todo_application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salavat.todo_application.model.Task;

import java.util.List;

// TaskRepository.java
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(boolean completed);
}