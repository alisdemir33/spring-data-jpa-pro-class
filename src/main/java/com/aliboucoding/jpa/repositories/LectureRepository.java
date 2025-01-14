package com.aliboucoding.jpa.repositories;

import com.aliboucoding.jpa.models.Lecture;
import com.aliboucoding.jpa.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {

}
