package com.jurajkarlubik.animals.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(unique = true, nullable = false)
    private String name;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private int age;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "breed_id", nullable = false)
    private Breed breed;

    public enum Gender {
        MALE, FEMALE
    }
}