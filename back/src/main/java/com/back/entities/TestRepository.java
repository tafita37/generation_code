package com.back.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test,Integer> {
    
}
