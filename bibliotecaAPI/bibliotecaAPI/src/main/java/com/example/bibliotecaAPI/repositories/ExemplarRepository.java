package com.example.bibliotecaAPI.repositories;

import com.example.bibliotecaAPI.models.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Integer> {
}