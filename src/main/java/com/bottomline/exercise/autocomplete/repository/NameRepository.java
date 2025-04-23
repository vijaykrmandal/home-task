package com.bottomline.exercise.autocomplete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bottomline.exercise.autocomplete.entity.Name;

public interface NameRepository extends JpaRepository<Name, Long> {
	
}
