package com.aliboucoding.jpa.repositories;

import com.aliboucoding.jpa.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Integer> {
}
