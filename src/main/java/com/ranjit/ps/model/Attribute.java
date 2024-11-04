package com.ranjit.ps.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`key`") // Escapes `key` in SQL
    private String key;

    private String value;

    public Attribute() {
    }

    public Attribute(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
