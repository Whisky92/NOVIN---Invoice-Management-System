package com.novin.invoicemanagementsystem.repository;

import com.novin.invoicemanagementsystem.entity.Role;
import com.novin.invoicemanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

    @Query("SELECT u.roles FROM User u WHERE u.userName = ?1")
    Optional<List<Role>> findRolesByUserName(String username);
}
