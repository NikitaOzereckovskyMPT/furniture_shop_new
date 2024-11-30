// UserService.java
package com.web_project.furniture.service;

import com.web_project.furniture.model.UserModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserModel registerNewCustomer(UserModel user);
    UserModel registerNewUser(UserModel user);
    List<UserModel> getAllUsers();
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findById(UUID id);  // Добавлен метод
    boolean isCustomer(UserModel user);
    void updateUser(UserModel user);
    boolean isUsernameExists(String username);
    void deleteUser(UUID id, String currentUsername);  // Добавлен метод
}