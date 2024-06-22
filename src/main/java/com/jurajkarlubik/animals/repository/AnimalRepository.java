package com.jurajkarlubik.animals.repository;

import com.jurajkarlubik.animals.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}

