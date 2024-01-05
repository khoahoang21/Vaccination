package com.example.vaccination.repository;

import com.example.vaccination.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Integer> {
    List<News> findAllByOrderByPostdateDesc();
    List<News> findTop5ByOrderByPostdateDesc();
}
