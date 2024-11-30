package com.web_project.furniture.service;

import com.web_project.furniture.model.RoleEnum;
import com.web_project.furniture.model.UserModel;
import com.web_project.furniture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserModel registerNewCustomer(UserModel user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует!");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRole(RoleEnum.CUSTOMER);

        return userRepository.save(user);
    }

    @Override
    public UserModel registerNewUser(UserModel user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует!");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        if (user.getRole() == null) {
            user.setRole(RoleEnum.CUSTOMER);
        }

        return userRepository.save(user);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserModel> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean isCustomer(UserModel user) {
        return user.getRole() == RoleEnum.CUSTOMER;
    }

    @Override
    public void updateUser(UserModel user) {
        userRepository.save(user);
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void deleteUser(UUID id, String currentUsername) {
        UserModel user = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        if (user.getUsername().equals(currentUsername)) {
            throw new IllegalStateException("Невозможно удалить собственную учетную запись администратора");
        }

        userRepository.deleteById(id);
    }
}