package com.example.articlestore.service;

import com.example.articlestore.domain.Article;
import com.example.articlestore.domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Article article, MultipartFile file);
    void saveImageFile(User user, MultipartFile file);
}
