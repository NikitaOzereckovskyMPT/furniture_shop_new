package com.web_project.furniture.controllers;

import com.web_project.furniture.model.MaterialModel;
import com.web_project.furniture.service.MaterialService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/materials")
@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping("/all")
    public String getAllMaterials(Model model) {
        model.addAttribute("materials", materialService.findAllMaterials());
        return "all-materials";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("material", new MaterialModel());
        return "add-material";
    }

    @PostMapping("/add")
    public String addMaterial(@Valid @ModelAttribute("material") MaterialModel material,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "add-material";
        }
        materialService.addMaterial(material);
        return "redirect:/all-materials";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        MaterialModel material = materialService.findMaterialById(id)
                .orElseThrow(() -> new EntityNotFoundException("Материал не найден"));
        model.addAttribute("material", material);
        return "edit-material";
    }

    @PostMapping("/{id}/update")
    public String updateMaterial(@PathVariable UUID id,
                                 @Valid @ModelAttribute("material") MaterialModel material,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "edit-material";
        }
        material.setId(id);
        MaterialModel updated = materialService.updateMaterial(material);
        if (updated == null) {
            throw new EntityNotFoundException("Материал не найден");
        }
        return "redirect:/all-materials";
    }

    @PostMapping("/{id}/delete")
    public String deleteMaterial(@PathVariable UUID id) {
        materialService.deleteMaterial(id);
        return "redirect:/all-materials";
    }

    @GetMapping("/{id}")
    public String getMaterialDetails(@PathVariable UUID id, Model model) {
        MaterialModel material = materialService.findMaterialById(id)
                .orElseThrow(() -> new EntityNotFoundException("Материал не найден"));
        model.addAttribute("material", material);
        return "material-details";
    }
}