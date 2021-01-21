package com.example.articlestore.service;

import com.example.articlestore.domain.Article;
import com.example.articlestore.domain.User;

public interface LikeService {
    void like(User user, Article article);
}
