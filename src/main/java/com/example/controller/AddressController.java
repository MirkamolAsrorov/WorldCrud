package com.example.controller;


import com.example.repository.*;
import com.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    GalacticRepository galacticRepository;
    @Autowired
    PlanetRepository planetRepository;
    @Autowired
    ContinentRepository continentRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    DistrictRepository districtRepository;

    public String getAddressPage(Model model){
        model.addAttribute("list", galacticRepository.findAllByActiveTrue());
        model.addAttribute("list", planetRepository.findAllByActiveTrue());
        model.addAttribute("list", continentRepository.findAllByActiveTrue());
        model.addAttribute("list", countryRepository.findAllByActiveTrue());
        model.addAttribute("list", regionRepository.findAllByActiveTrue());
        model.addAttribute("list", districtRepository.findAllByActiveTrue());
        return "address/address";
    }

}
