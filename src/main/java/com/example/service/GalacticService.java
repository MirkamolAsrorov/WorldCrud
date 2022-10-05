package com.example.service;


import com.example.entity.Planet;
import com.example.dto.ApiResponse;
import com.example.entity.Galactic;
import com.example.repository.GalacticRepository;
import com.example.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GalacticService {
    @Autowired
    GalacticRepository galacticRepository;
    @Autowired
    PlanetRepository planetRepository;

    public ApiResponse add(Galactic galactic){
        Galactic save = galacticRepository.save(galactic);
        return new ApiResponse("Saved",true,save);
    }

//public ApiResponse delete(Country country){
//countryRepository.delete(country);
//return new ApiResponse("Deleted",true);
//
//}

    public void edit(Integer id, Galactic galactic) {
        Optional<Galactic> optionalGalactic = galacticRepository.findById(id);
        Galactic galactic1 = optionalGalactic.get();
        galactic1.setName(galactic.getName());
        Galactic save = galacticRepository.save(galactic1);

        List<Planet> byGalactic_id = planetRepository.findByGalactic_Id(id);
        for (Planet planet : byGalactic_id) {
            planet.setGalactic(save);
        }
        planetRepository.saveAll(byGalactic_id);

    }
}
