package com.example.controller;


import com.example.dto.ApiResponse;
import com.example.dto.PlanetDTO;
import com.example.entity.Planet;
import com.example.repository.GalacticRepository;
import com.example.repository.PlanetRepository;
import com.example.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller

@RequestMapping("/planet")
public class PlanetController {
    @Autowired
    PlanetRepository planetRepository;
    @Autowired
    PlanetService planetService;
    @Autowired
    GalacticRepository galacticRepository;


    @GetMapping
    public String getRegionPage(Model model){
        model.addAttribute("list", planetRepository.findAllByActiveTrue());
        return "planet/planet";
    }

    @GetMapping("/back")
    public String comeBack() {
        return "index";
    }

    @GetMapping("/add")
    public String addCountry(Model model){
        model.addAttribute("galacticList", galacticRepository.findAllByActiveTrue());
        return "planet/planet-add";
    }

    @PostMapping("/add")
    public String saveCountry(Model model, @ModelAttribute PlanetDTO dto) {
        planetService.add(dto);
        return "redirect:/planet";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Planet> optionalPlanet = planetRepository.findById(id);
        if (!optionalPlanet.isPresent()) return "Xatolik!";
        model.addAttribute("edited", optionalPlanet.get());
        model.addAttribute("galacticList", galacticRepository.findAllByActiveTrue());
        return "planet/planet-edit";
    }

    @PostMapping("/edit/{id}")
    public String editDepartment(@PathVariable Integer id, @ModelAttribute PlanetDTO planetDTO) {
        ApiResponse response = planetService.edit(id, planetDTO);
        System.out.println(response);
        return "redirect:/planet";
    }


    @GetMapping("/delete/{id}") //1 45 24 90
    public String delete(@PathVariable Integer id) {
        Optional<Planet> optionalPlanet = planetRepository.findById(id);
        Planet planet = optionalPlanet.get();
        planet.setActive(false);
        planetRepository.save(planet);
        return "redirect:/planet";
    }
}
