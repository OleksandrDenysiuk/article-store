package com.example.articlestore.service;

import com.example.articlestore.domain.Article;
import com.example.articlestore.repo.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    //setting current date to article and save it
    @Override
    public Article save(Article article) {
        article.setDate(new Date());
        return articleRepository.save(article);
    }

    //todo: create update method

    //deletes article from db
    @Override
    public void delete(Article article) {
        articleRepository.delete(article);
    }

    //returns all articles from db
    @Override
    public List<Article> getAll() {
        return (List<Article>) articleRepository.findAll();
    }
}
