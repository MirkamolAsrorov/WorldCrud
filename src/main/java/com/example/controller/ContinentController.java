package com.example.controller;


import com.example.dto.ApiResponse;
import com.example.dto.ContinentDTO;
import com.example.entity.Continent;
import com.example.repository.ContinentRepository;
import com.example.repository.PlanetRepository;
import com.example.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RequestMapping("/continent")
public class ContinentController {
    @Autowired
    ContinentRepository continentRepository;
    @Autowired
    ContinentService continentService;
    @Autowired
    PlanetRepository planetRepository;


    @GetMapping
    public String getRegionPage(Model model){
        model.addAttribute("list", continentRepository.findAllByActiveTrue());
        return "continent/continent";
    }

    @GetMapping("/back")
    public String comeBack() {
        return "index";
    }

    @GetMapping("/add")
    public String addCountry(Model model){
        model.addAttribute("planetList", planetRepository.findAllByActiveTrue());
        return "continent/continent-add";
    }

    @PostMapping("/add")
    public String saveCountry(Model model, @ModelAttribute ContinentDTO dto) {
        continentService.add(dto);
        return "redirect:/continent";
    }
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Continent> optionalContinent = continentRepository.findById(id);
        if (!optionalContinent.isPresent()) return "Xatolik!";
        model.addAttribute("edited", optionalContinent.get());
        model.addAttribute("planetList", planetRepository.findAllByActiveTrue());
        return "continent/continent-edit";
    }

    @PostMapping("/edit/{id}")
    public String editDepartment(@PathVariable Integer id, @ModelAttribute ContinentDTO continentDTO) {
        ApiResponse response = continentService.edit(id, continentDTO);
        System.out.println(response);
        return "redirect:/continent";
    }


    @GetMapping("/delete/{id}") //1 45 24 90
    public String delete(@PathVariable Integer id) {
        Optional<Continent> optionalContinent = continentRepository.findById(id);
        Continent continent = optionalContinent.get();
        continent.setActive(false);
        continentRepository.save(continent);
        return "redirect:/continent";
    }
}
