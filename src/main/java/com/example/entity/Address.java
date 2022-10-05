package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String street_name;

    @Column(nullable = false,unique = true)
    private Integer homeNumber;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "galactic_id", nullable = false)
    private Galactic galactic;
    @ManyToOne
    @JoinColumn(name = "planet_id", nullable = false)
    private Planet planet;
    @ManyToOne
    @JoinColumn(name = "continent_id", nullable = false)
    private Continent continent;
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

}
