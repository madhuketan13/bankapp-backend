package com.microservice.authservice.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Roles")
@NoArgsConstructor
@Getter
@Setter
public class Role extends IdBasedEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true)
    private ERole name;

    public Role(ERole name){
        this.name = name;
    }

    public ERole getName() {
        return name;
    }
}
