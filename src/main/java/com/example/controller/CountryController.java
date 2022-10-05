package com.example.controller;


import com.example.dto.ApiResponse;
import com.example.dto.CountryDTO;
import com.example.entity.Country;
import com.example.repository.ContinentRepository;
import com.example.repository.CountryRepository;
import com.example.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RequestMapping("/country")
public class CountryController {
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    CountryService countryService;
    @Autowired
    ContinentRepository continentRepository;


    @GetMapping
    public String getRegionPage(Model model){
        model.addAttribute("list", countryRepository.findAllByActiveTrue());
        return "country/country";
    }

    @GetMapping("/back")
    public String comeBack() {
        return "index";
    }

    @GetMapping("/add")
    public String addCountry(Model model){
        model.addAttribute("continentList", continentRepository.findAllByActiveTrue());
        return "country/country-add";
    }

    @PostMapping("/add")
    public String saveCountry(Model model, @ModelAttribute CountryDTO dto) {
        countryService.add(dto);
        return "redirect:/country";
    }
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Country> optionalPlanet = countryRepository.findById(id);
        if (!optionalPlanet.isPresent()) return "Xatolik!";
        model.addAttribute("edited", optionalPlanet.get());
        model.addAttribute("continentList", continentRepository.findAllByActiveTrue());
        return "country/country-edit";
    }

    @PostMapping("/edit/{id}")
    public String editDepartment(@PathVariable Integer id, @ModelAttribute CountryDTO countryDTO) {
        ApiResponse response = countryService.edit(id, countryDTO);
        System.out.println(response);
        return "redirect:/country";
    }


    @GetMapping("/delete/{id}") //1 45 24 90
    public String delete(@PathVariable Integer id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        Country country = optionalCountry.get();
        country.setActive(false);
        countryRepository.save(country);
        return "redirect:/country";
    }
}
