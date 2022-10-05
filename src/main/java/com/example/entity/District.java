package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class District {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false,unique = true)
    private String name;


    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
    }
