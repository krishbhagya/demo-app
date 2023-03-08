package com.example.demo.repository;

import com.example.demo.domain.Spice;
import org.springframework.data.repository.CrudRepository;

public interface SpicesRepository extends CrudRepository<Spice, Integer> {
}
