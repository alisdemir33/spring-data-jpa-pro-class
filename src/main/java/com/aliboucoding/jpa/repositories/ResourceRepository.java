package com.aliboucoding.jpa.repositories;

import com.aliboucoding.jpa.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource,Integer>  {
}
