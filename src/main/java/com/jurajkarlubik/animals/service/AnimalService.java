package com.jurajkarlubik.animals.service;

import com.jurajkarlubik.animals.dto.AnimalDto;
import com.jurajkarlubik.animals.dto.AnimalWithDetailsDto;

import java.util.List;

public interface AnimalService {
    AnimalDto addAnimal(AnimalDto animalDto);
    void removeAnimal(Long id);
    AnimalDto getAnimal(Long id);
    AnimalDto updateAnimal(AnimalDto animalDto);
    List<AnimalDto> getAnimals();
    List<AnimalWithDetailsDto> getAnimalsWithDetails();
}
