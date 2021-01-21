package com.example.articlestore.service;


import com.example.articlestore.domain.Article;

import java.util.List;

public interface ArticleService {
    Article save(Article article);

    void delete(Article article);

    List<Article> getAll();
}
