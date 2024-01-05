package com.example.vaccination.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private int newsId;

    @Column(name = "content", length = 1000)
    @NotEmpty(message = "Content is required")
    private String content;

    @Column(name = "preview", length = 100)
    @NotEmpty(message = "Preview is required")
    private String preview;

    @Column(name = "title", length = 70)
    @NotEmpty(message = "Title is required")
    private String title;

    @Column(name = "postdate")
    private Date postdate;
}