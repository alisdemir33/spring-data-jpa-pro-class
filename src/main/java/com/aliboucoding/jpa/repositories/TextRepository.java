package com.aliboucoding.jpa.repositories;

import com.aliboucoding.jpa.models.Text;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository extends JpaRepository<Text,Integer> {
}
