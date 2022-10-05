package com.example.repository;

import com.example.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

;import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByActiveTrue();

}
