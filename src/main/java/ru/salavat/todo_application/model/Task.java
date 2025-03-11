package ru.salavat.todo_application.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// Task.java
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private MyUser owner;

    public MyUser getOwner() {
        return owner;
    }

    public void setOwner(MyUser owner) {
        this.owner = owner;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}