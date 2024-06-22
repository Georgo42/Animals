package com.jurajkarlubik.animals.repository;

import com.jurajkarlubik.animals.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed, Long> {
}

