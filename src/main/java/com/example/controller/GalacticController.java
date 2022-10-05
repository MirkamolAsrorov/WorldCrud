package com.example.controller;


import com.example.entity.Galactic;
import com.example.repository.GalacticRepository;
import com.example.service.GalacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/galactic")
public class GalacticController {
    @Autowired
    GalacticRepository galacticRepository;
    @Autowired
    GalacticService galacticService;

    @GetMapping
    public String getGalacticPage(Model model) {
        model.addAttribute("list", galacticRepository.findAllByActiveTrue());
        return "galactic/galactic";
    }

    @GetMapping("/back")
    public String comeBack() {
        return "index";
    }

    @GetMapping("/add")
    public String addGalactic() {
        return "galactic/galactic-add";
    }

    @PostMapping("/add")
    public String saveCountry(Model model, @ModelAttribute Galactic galactic) {
        galacticService.add(galactic);
        return "redirect:/galactic";
    }

    @GetMapping("/delete/{id}")
    public String activeFalse(@PathVariable Integer id) {
        Optional<Galactic> optionalGalactic = galacticRepository.findById(id);
        Galactic galactic = optionalGalactic.get();
        galactic.setActive(false);
        galacticRepository.save(galactic);
        return "redirect:/galactic";
    }

    @GetMapping("/edit/{id}")
    public String editPage(Model model, @PathVariable(value = "id") Integer id) {
        Optional<Galactic> byId = galacticRepository.findById(id);
        Galactic galactic = byId.get();
        model.addAttribute("galactic", galactic);
        return "galactic/galactic-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable(value = "id") Integer id, @ModelAttribute Galactic galactic) {
        Optional<Galactic> optionalGalactic = galacticRepository.findById(id);
        Galactic galactic1 = optionalGalactic.get();
        galactic1.setId(galactic.getId());
        galactic1.setName(galactic.getName());
        galacticRepository.save(galactic1);
        return "redirect:/galactic";
    }

}
