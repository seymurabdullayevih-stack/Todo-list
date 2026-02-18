package com.proyekt.user.repository;

import com.proyekt.user.model.Todo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryTodo extends JpaRepository<Todo,Long> {


    List<Todo> findByUserId(Long UserId);

    Optional<Todo> findByIdAndUserId(Long id, Long userId);

    @Transactional
    void deleteByIdAndUserId(Long id, Long userId);
}