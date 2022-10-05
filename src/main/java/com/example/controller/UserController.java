package com.example.controller;

import com.example.dto.ApiResponse;
import com.example.dto.RegionDTO;
import com.example.entity.Region;
import com.example.repository.CountryRepository;
import com.example.repository.RegionRepository;
import com.example.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
public class UserController {
    @Autowired
    RegionRepository repository;
    @Autowired
    RegionService regionService;
    @Autowired
    CountryRepository countryRepository;

//
//    @GetMapping
//    public String getRegionPage(Model model){
//        model.addAttribute("list",repository.findAllByActiveTrue());
//        return "user/user";
//    }


    @GetMapping("/add")
    public String addCountry(Model model){
        model.addAttribute("countryList", countryRepository.findAllByActiveTrue());
        return "region/region-add";
    }

    @PostMapping("/add")
    public String saveCountry(Model model, @ModelAttribute RegionDTO dto) {
        regionService.add(dto);
        return "redirect:/region";
    }
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Region> optionalRegion = repository.findById(id);
        if (!optionalRegion.isPresent()) return "Xatolik!";
        model.addAttribute("edited", optionalRegion.get());
        model.addAttribute("countryList", countryRepository.findAllByActiveTrue());
        return "region/region-edit";
    }

    @PostMapping("/edit/{id}")
    public String editDepartment(@PathVariable Integer id, @ModelAttribute RegionDTO regionDTO) {
        ApiResponse response = regionService.edit(id, regionDTO);
        System.out.println(response);
        return "redirect:/region";
    }


    @GetMapping("/delete/{id}") //1 45 24 90
    public String delete(@PathVariable Integer id) {
        Optional<Region> optionalRegion = repository.findById(id);
        Region region = optionalRegion.get();
        region.setActive(false);
        repository.save(region);
        return "redirect:/region";
    }
}
