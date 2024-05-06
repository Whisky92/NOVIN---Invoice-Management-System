package com.novin.invoicemanagementsystem.repository;

import com.novin.invoicemanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
