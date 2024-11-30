package com.web_project.furniture.repository;

import com.web_project.furniture.model.RoleEnum;
import com.web_project.furniture.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    List<UserModel> findByRole(RoleEnum role);  // Изменено с findByRolesContains на findByRole
}