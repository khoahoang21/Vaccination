package com.example.vaccination.service;

import com.example.vaccination.model.entity.News;

import java.util.List;

public interface NewsServices {
    List<News> getAllNews();

    List<News> findAllByOrderByPostdateDesc();
    void createNews(News news);

    News findbyId(int Id);

    void updateNews(News news);

    void deleteNews(int Id);
    List<News> findTop5ByOrderByPostdateDesc();
}
