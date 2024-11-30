package com.web_project.furniture.controllers;

import com.web_project.furniture.model.RoleEnum;
import com.web_project.furniture.model.UserModel;
import com.web_project.furniture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public String userView(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("user_list", userService.getAllUsers());
        model.addAttribute("currentUser", currentUser);
        return "all-users";
    }

    @GetMapping("/users/{id}")
    public String detailView(@PathVariable UUID id, Model model) {
        UserModel user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пользователя не существует:" + id));
        model.addAttribute("user_object", user);
        return "user-details";
    }

    @GetMapping("/users/{id}/edit")
    public String editUserForm(@PathVariable UUID id, Model model) {
        UserModel user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пользователя не существует:" + id));
        model.addAttribute("user_object", user);
        model.addAttribute("roles", RoleEnum.values());
        return "edit-user";
    }

    @PostMapping("/users/{id}/edit")
    public String editUser(@PathVariable UUID id,
                           @RequestParam String username,
                           @RequestParam RoleEnum role,
                           @AuthenticationPrincipal UserDetails currentUser) {
        UserModel user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пользователя не существует:" + id));

        // Проверяем, не пытается ли админ изменить свою роль
        if (user.getUsername().equals(currentUser.getUsername()) &&
                user.getRole() != role) {
            return "redirect:/admin/users?error=cant-change-own-role";
        }

        user.setUsername(username);
        user.setRole(role);
        userService.updateUser(user);
        return "redirect:/admin/users/" + id;
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable UUID id,
                             @AuthenticationPrincipal UserDetails currentUser) {
        try {
            userService.deleteUser(id, currentUser.getUsername());
            return "redirect:/admin/users";
        } catch (IllegalStateException e) {
            return "redirect:/admin/users?error=cant-delete-yourself";
        }
    }
}