package com.example.articlestore.repo;

import com.example.articlestore.domain.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ArticleRepository extends CrudRepository<Article, Long> {
    Optional<Article> findById(Long id);
}
