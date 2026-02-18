package com.proyekt.user.repository;

import com.proyekt.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryUser extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String username);
}
