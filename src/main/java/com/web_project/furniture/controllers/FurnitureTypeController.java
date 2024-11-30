package com.web_project.furniture.controllers;

import com.web_project.furniture.model.FurnitureTypeModel;
import com.web_project.furniture.service.FurnitureTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/furnitureTypes")    // Обратите внимание на написание
public class FurnitureTypeController {

    private final FurnitureTypeService furnitureTypeService;

    @Autowired
    public FurnitureTypeController(FurnitureTypeService furnitureTypeService) {
        this.furnitureTypeService = furnitureTypeService;
    }

    @GetMapping("/all")
    public String getAllFurnitureTypes(Model model) {
        model.addAttribute("furnitureTypes", furnitureTypeService.findAllFurnitureTypes());
        return "all-furniture-types";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("furnitureType", new FurnitureTypeModel());
        return "add-furniture-type";
    }

    @PostMapping("/add")
    public String addFurnitureType(@Valid @ModelAttribute("furnitureType") FurnitureTypeModel furnitureType,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "add-furniture-type";
        }
        furnitureTypeService.addFurnitureType(furnitureType);
        return "redirect:/furnitureTypes/all";
    }

    @GetMapping("/edit/{id}")    // Изменен порядок в пути
    public String showEditForm(@PathVariable UUID id, Model model) {
        FurnitureTypeModel furnitureType = furnitureTypeService.findFurnitureTypeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid furniture type Id:" + id));
        model.addAttribute("furnitureType", furnitureType);
        return "edit-furniture-type";
    }

    @PostMapping("/edit/{id}")    // Изменен путь для соответствия
    public String updateFurnitureType(@PathVariable UUID id,
                                      @Valid @ModelAttribute("furnitureType") FurnitureTypeModel furnitureType,
                                      BindingResult result) {
        if (result.hasErrors()) {
            furnitureType.setId(id);
            return "edit-furniture-type";
        }
        furnitureType.setId(id);
        furnitureTypeService.updateFurnitureType(furnitureType);
        return "redirect:/furnitureTypes/all";
    }

    @PostMapping("/delete/{id}")    // Изменен порядок в пути
    public String deleteFurnitureType(@PathVariable UUID id) {
        furnitureTypeService.deleteFurnitureType(id);
        return "redirect:/furnitureTypes/all";
    }

    @GetMapping("/details/{id}")    // Изменен порядок в пути
    public String getDetails(@PathVariable UUID id, Model model) {
        FurnitureTypeModel furnitureType = furnitureTypeService.findFurnitureTypeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid furniture type Id:" + id));
        model.addAttribute("furnitureType", furnitureType);
        return "furniture-type-details";
    }
}