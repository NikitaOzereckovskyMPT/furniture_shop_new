package com.web_project.furniture.controllers;

import com.web_project.furniture.model.FurnitureModel;
import com.web_project.furniture.model.MaterialModel;
import com.web_project.furniture.service.FurnitureService;
import com.web_project.furniture.service.FurnitureTypeService;
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
@RequestMapping("/furnitures")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class FurnitureController {
    @Autowired
    private FurnitureService furnitureService;

    @Autowired
    private FurnitureTypeService furnitureTypeService;

    @Autowired
    private MaterialService materialService;

    @GetMapping("/all")
    public String getAllFurnitures(Model model) {
        model.addAttribute("furnitures", furnitureService.findAllFurnitures());
        return "AllFurniture";
    }

    @GetMapping("/add")
    public String showAddFurnitureForm(Model model) {
        model.addAttribute("furniture", new FurnitureModel());
        model.addAttribute("furnitureTypes", furnitureTypeService.findAllFurnitureTypes());
        model.addAttribute("materials", materialService.findAllMaterials());
        return "AddFurniture";
    }

    @PostMapping("/add")
    public String addFurniture(@Valid @ModelAttribute("furniture") FurnitureModel furniture,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("furnitureTypes", furnitureTypeService.findAllFurnitureTypes());
            model.addAttribute("materials", materialService.findAllMaterials());
            return "AddFurniture";
        }

        for (UUID materialId : furniture.getMaterialIds()) {
            MaterialModel material = materialService.findMaterialById(materialId)
                    .orElseThrow(() -> new EntityNotFoundException("Материал не найден"));
            furniture.addMaterial(material);
        }

        furnitureService.addFurniture(furniture);
        return "redirect:/furnitures/all";
    }

    @PostMapping("/update")
    public String updateFurniture(@Valid @ModelAttribute("furniture") FurnitureModel furniture,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("furnitureTypes", furnitureTypeService.findAllFurnitureTypes());
            model.addAttribute("materials", materialService.findAllMaterials());
            return "EditFurniture";
        }

        furniture.getMaterials().clear();
        for (UUID materialId : furniture.getMaterialIds()) {
            MaterialModel material = materialService.findMaterialById(materialId)
                    .orElseThrow(() -> new EntityNotFoundException("Материал не найден"));
            furniture.addMaterial(material);
        }

        FurnitureModel updatedFurniture = furnitureService.updateFurniture(furniture);
        if (updatedFurniture == null) {
            throw new EntityNotFoundException("Мебель не найдена");
        }
        return "redirect:/furnitures/all";
    }

    @PostMapping("/delete")
    public String deleteFurniture(@RequestParam UUID id) {
        furnitureService.deleteFurniture(id);
        return "redirect:/furnitures/all";
    }

    @GetMapping("/details/{id}")
    public String getIdFurniture(@PathVariable("id") UUID id, Model model) {
        FurnitureModel furniture = furnitureService.findFurnitureById(id)
                .orElseThrow(() -> new EntityNotFoundException("Мебель не найдена"));
        model.addAttribute("furniture", furniture);
        model.addAttribute("furnitureTypes", furnitureTypeService.findAllFurnitureTypes());
        model.addAttribute("materials", materialService.findAllMaterials());
        return "FurnitureDetails";
    }

    @GetMapping("/edit/{id}")
    public String editFurniture(@PathVariable UUID id, Model model) {
        FurnitureModel furniture = furnitureService.findFurnitureById(id)
                .orElseThrow(() -> new EntityNotFoundException("Мебель не найдена"));
        model.addAttribute("furniture", furniture);
        model.addAttribute("furnitureTypes", furnitureTypeService.findAllFurnitureTypes());
        model.addAttribute("materials", materialService.findAllMaterials());
        return "EditFurniture";
    }
}