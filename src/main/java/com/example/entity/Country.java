package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Country {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(nullable = false,unique = true)
        private String name;

        @Column(nullable = false)
        private boolean active = true;

        @ManyToOne
        @JoinColumn(name = "continent_id", nullable = false)
        private Continent continent;

}
