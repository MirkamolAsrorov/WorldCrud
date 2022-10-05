package com.example.service;


import com.example.dto.CountryDTO;
import com.example.entity.Continent;
import com.example.entity.Country;
import com.example.dto.ApiResponse;
import com.example.repository.ContinentRepository;
import com.example.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    ContinentRepository continentRepository;
    @Autowired
    CountryRepository countryRepository;

    public ApiResponse add(CountryDTO countryDTO) {
        Optional<Continent> optionalContinent = continentRepository.findById(countryDTO.getContinentId());
        if (optionalContinent.isEmpty()) return new ApiResponse("The data don't have in database", false);
        Continent continent1 = optionalContinent.get();

        Country country = new Country();
        country.setName(countryDTO.getName());
        country.setContinent(continent1);

        Country save = countryRepository.save(country);
        return new ApiResponse("saved", true, save);
    }

    public ApiResponse edit(Integer id,CountryDTO countryDTO) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        Country country = optionalCountry.get();

        Optional<Continent> optionalContinent = continentRepository.findById(countryDTO.getContinentId());
        Continent continent = optionalContinent.get();

        country.setName(countryDTO.getName());
        country.setContinent(continent);

        countryRepository.save(country);
        return new ApiResponse("Updated!", true);
    }
}












