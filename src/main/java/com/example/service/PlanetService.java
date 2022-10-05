package com.example.service;


import com.example.dto.PlanetDTO;
import com.example.entity.Galactic;
import com.example.entity.Planet;
import com.example.dto.ApiResponse;
import com.example.repository.GalacticRepository;
import com.example.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanetService {
    @Autowired
    GalacticRepository galacticRepository;
    @Autowired
    PlanetRepository planetRepository;

    public ApiResponse add(PlanetDTO planetDTO) {
        Optional<Galactic> optionalGalactic = galacticRepository.findById(planetDTO.getGalacticId());
        if (optionalGalactic.isEmpty()) return new ApiResponse("The data don't have in database", false);
        Galactic galactic = optionalGalactic.get();

        Planet planet = new Planet();
        planet.setName(planetDTO.getName());
        planet.setGalactic(galactic);

        Planet save = planetRepository.save(planet);
        return new ApiResponse("saved", true, save);
    }

    public ApiResponse edit(Integer id, PlanetDTO planetDTO) {
        Optional<Planet> optionalPlanet = planetRepository.findById(id);
        Planet planet = optionalPlanet.get();

        Optional<Galactic> optionalGalactic = galacticRepository.findById(planetDTO.getGalacticId());
        Galactic galactic = optionalGalactic.get();

        planet.setName(planetDTO.getName());
        planet.setGalactic(galactic);

        planetRepository.save(planet);
        return new ApiResponse("Updated!", true);
    }

}
