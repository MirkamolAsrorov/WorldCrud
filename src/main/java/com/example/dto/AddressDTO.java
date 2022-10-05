package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDTO {
    private String name;
    private Integer galacticId;
    private Integer planetId;
    private Integer continentId;
    private Integer countryId;
    private Integer regionId;
    private Integer districtId;

}
