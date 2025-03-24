package com.example.ruokintaseuranta.repository;

import kissat.ruokintaseuranta.domain.Ateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AteriaRepository extends JpaRepository<Ateria, Long> {
}
