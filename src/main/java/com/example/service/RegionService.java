package com.example.service;


import com.example.dto.RegionDTO;
import com.example.entity.Country;
import com.example.entity.Region;
import com.example.dto.ApiResponse;
import com.example.repository.CountryRepository;
import com.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    RegionRepository repository;

    public ApiResponse add(RegionDTO regionDTO) {
        Optional<Country> optionalCountry = countryRepository.findById(regionDTO.getCountryId());
        if (optionalCountry.isEmpty()) return new ApiResponse("The data don't have in database", false);
        Country country = optionalCountry.get();

        Region region = new Region();
        region.setName(regionDTO.getName());
        region.setCountry(country);

        Region save = repository.save(region);
        return new ApiResponse("saved", true, save);
    }

    public ApiResponse edit(Integer id, RegionDTO regionDTO) {
        Optional<Region> optionalRegion = repository.findById(id);
        Region region = optionalRegion.get();

        Optional<Country> optionalCountry = countryRepository.findById(regionDTO.getCountryId());
        Country country = optionalCountry.get();

        region.setName(regionDTO.getName());
        region.setCountry(country);

        repository.save(region);
        return new ApiResponse("Updated!", true);
    }
}