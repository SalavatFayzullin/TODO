package ru.salavat.todo_application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salavat.todo_application.model.MyUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}