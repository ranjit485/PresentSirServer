package com.ranjit.ps.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Bus {

    @Id
    private Long busId;  // If you're manually assigning it, remove @GeneratedValue

    private String routeName;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new HashSet<>(); // Multiple users can be assigned to a single bus

    // Constructors are optional when using Lombok
    public Bus() {
    }

    public Bus(Long busId, String routeName) {
        this.busId = busId;
        this.routeName = routeName;
    }
}
