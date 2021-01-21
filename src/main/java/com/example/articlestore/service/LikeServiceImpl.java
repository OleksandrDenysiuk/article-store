package com.example.articlestore.service;

import com.example.articlestore.domain.Article;
import com.example.articlestore.domain.User;
import com.example.articlestore.repo.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService{

    private final ArticleRepository articleRepository;

    public LikeServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    //adds new user to list of users which liked article
    @Override
    public void like(User user, Article article) {
        article.getLikedUsers().add(user);
        articleRepository.save(article);
    }
}
