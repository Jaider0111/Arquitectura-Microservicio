package com.sofka.clientmanagement.infrastructure.adapter.repository;

import com.sofka.clientmanagement.domain.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
  }