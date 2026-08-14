package com.example.librarybookingsystem.repository;

import com.example.librarybookingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}