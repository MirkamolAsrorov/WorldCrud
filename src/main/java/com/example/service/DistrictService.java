package com.example.service;


import com.example.dto.ApiResponse;
import com.example.dto.DistrictDTO;
import com.example.entity.District;
import com.example.entity.Region;
import com.example.repository.DistrictRepository;
import com.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DistrictService {
@Autowired
RegionRepository repository;
@Autowired
DistrictRepository districtRepository;

public ApiResponse add(DistrictDTO districtDTO){
    Optional<Region> optional =repository.findById(districtDTO.getRegionId());
if (optional.isEmpty()) return new ApiResponse("The data don't have in database",false);
Region region = optional.get();
District district = new District();
district.setName(districtDTO.getName());
district.setRegion(region);
District save = districtRepository.save(district);

return new ApiResponse("Saved", true,save);
}

public ApiResponse edit(Integer id, DistrictDTO districtDTO) {
        Optional<District> optionalDistrict = districtRepository.findById(id);
        District district = optionalDistrict.get();

        Optional<Region> optionalRegion = repository.findById(districtDTO.getRegionId());
        Region region = optionalRegion.get();

        district.setName(districtDTO.getName());
        district.setRegion(region);

        districtRepository.save(district);
        return new ApiResponse("Updated!", true);
    }

}
