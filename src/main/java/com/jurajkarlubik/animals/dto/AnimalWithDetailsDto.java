package com.jurajkarlubik.animals.dto;

import lombok.Data;

@Data
public class AnimalWithDetailsDto {
    private Long id;
    private String name;
    private int age;
    private String breedName;
    private String gender;
}
