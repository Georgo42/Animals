package com.jurajkarlubik.animals.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnimalDto {
    private Long id;

    @NotNull(message = "Name is mandatory")
    private String name;

    @Min(value = 1, message = "Age must be greater than 0")
    @NotNull(message = "Age is mandatory")
    private int age;

    @NotNull(message = "Breed ID is mandatory")
    private Long breedId;

    @NotNull(message = "Gender is mandatory")
    private String gender;
}

