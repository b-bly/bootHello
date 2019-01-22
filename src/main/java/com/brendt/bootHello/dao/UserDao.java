package com.brendt.bootHello.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brendt.bootHello.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
