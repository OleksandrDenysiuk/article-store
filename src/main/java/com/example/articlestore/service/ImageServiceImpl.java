package com.example.articlestore.service;

import com.example.articlestore.domain.Article;
import com.example.articlestore.domain.User;
import com.example.articlestore.repo.ArticleRepository;
import com.example.articlestore.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ImageServiceImpl(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    //sets image for article and save it
    @Override
    public void saveImageFile(Article article, MultipartFile file) {

        try {
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            article.setImage(byteObjects);

            articleRepository.save(article);
        } catch (IOException e) {
            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }

    //sets image for user and save it
    @Override
    public void saveImageFile(User user, MultipartFile file) {
        try {
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            user.setImage(byteObjects);

            userRepository.save(user);
        } catch (IOException e) {
            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }
}

