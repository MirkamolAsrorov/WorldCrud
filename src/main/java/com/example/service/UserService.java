package com.example.service;

import com.example.dto.ApiResponse;
import com.example.dto.UserDTO;
import com.example.entity.Address;
import com.example.entity.Users;
import com.example.repository.AddressRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;

    public ApiResponse add(UserDTO userDTO) {
        Optional<Address> optionalAddress = addressRepository.findById(userDTO.getAddressId());
        if (optionalAddress.isEmpty()) return new ApiResponse("The data don't have in database", false);
        Address address = optionalAddress.get();

        Users user = new Users();
        user.setName(userDTO.getName());
        user.setAddress(address);

        Users save = userRepository.save(user);
        return new ApiResponse("saved", true, save);
    }


    public ApiResponse edit(Integer id,UserDTO userDTO) {
        Optional<Users> optionalUser = userRepository.findById(id);
        Users user = optionalUser.get();

        Optional<Address> optionalAddress = addressRepository.findById(userDTO.getAddressId());
        Address address = optionalAddress.get();

        user.setName(userDTO.getName());
        user.setAddress(address);

        userRepository.save(user);
        return new ApiResponse("Updated!", true);
    }
}
