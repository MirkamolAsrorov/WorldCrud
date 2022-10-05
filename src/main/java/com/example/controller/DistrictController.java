package com.example.controller;


import com.example.dto.ApiResponse;
import com.example.dto.DistrictDTO;
import com.example.entity.District;
import com.example.repository.DistrictRepository;
import com.example.repository.RegionRepository;
import com.example.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Controller

@RequestMapping("/district")
public class DistrictController {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    DistrictService districtService;
    @Autowired
    RegionRepository regionRepository;


    @GetMapping
    public String getRegionPage(Model model){
 List<District> allDistrictTrue  = districtRepository.findAllByActiveTrue();
        model.addAttribute("list",allDistrictTrue);
        return "district/district";
    }

    @GetMapping("/back")
    public String comeBack() {
        return "index";
    }

    @GetMapping("/add")
    public String addCountry(Model model){
        model.addAttribute("regionList", regionRepository.findAllByActiveTrue());
        return "district/district-add";
    }

    @PostMapping("/add")
    public String saveCountry(Model model, @ModelAttribute DistrictDTO dto) {
        districtService.add(dto);
        return "redirect:/district";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if (!optionalDistrict.isPresent()) return "Xatolik!";
        model.addAttribute("edited", optionalDistrict.get());
        model.addAttribute("regionList", regionRepository.findAllByActiveTrue());
        return "district/district-edit";
    }

    @PostMapping("/edit/{id}")
    public String editDepartment(@PathVariable Integer id, @ModelAttribute DistrictDTO districtDTO) {
        ApiResponse response = districtService.edit(id, districtDTO);
        return "redirect:/district";
    }



    @GetMapping("/delete/{id}") //1 45 24 90
    public String delete(@PathVariable Integer id) {
        Optional<District> optionalDistrict = districtRepository.findById(id);
        District district = optionalDistrict.get();
        district.setActive(false);
        districtRepository.save(district);
        return "redirect:/district";
    }

}
