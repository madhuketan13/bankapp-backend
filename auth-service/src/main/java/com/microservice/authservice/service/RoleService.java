package com.microservice.authservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservice.authservice.model.ERole;
import com.microservice.authservice.model.Role;
import com.microservice.authservice.repository.RoleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Optional<Role> findByName(ERole name) {
        return roleRepository.findByName(name);
    }

}
