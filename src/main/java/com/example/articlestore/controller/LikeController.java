package com.example.articlestore.controller;

import com.example.articlestore.domain.Article;
import com.example.articlestore.domain.User;
import com.example.articlestore.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/*
The controller is responsible for liking articles.
 */
@Slf4j
@Controller
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    //likes article and returns article index page
    @GetMapping("article/{articleId}/like")
    public String like(@AuthenticationPrincipal User user,
                       @PathVariable("articleId") Article article){

        if(!article.isLikedUser(user)){
            likeService.like(user,article);
        }

        log.debug("user _id: " + user.getId() + " liked article _id: " + article.getId());

        return "redirect:/article/read/" + article.getId();
    }
}
