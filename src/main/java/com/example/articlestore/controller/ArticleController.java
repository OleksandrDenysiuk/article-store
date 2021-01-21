package com.example.articlestore.controller;


import com.example.articlestore.domain.Article;
import com.example.articlestore.domain.User;
import com.example.articlestore.service.ArticleService;
import com.example.articlestore.service.ImageService;
import com.example.articlestore.service.UserService;
import com.example.articlestore.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


/*
The controller is responsible for processing requests related to articles.
 */

@Slf4j
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;
    private final ImageService imageService;
    private final ArticleValidator articleValidator;

    public ArticleController(ArticleService articleService, UserService userService, ImageService imageService, ArticleValidator articleValidator) {
        this.articleService = articleService;
        this.userService = userService;
        this.imageService = imageService;
        this.articleValidator = articleValidator;
    }

    //the method is responsible for covering all publications of the authenticated user
    @GetMapping("/user_articles")
    public String list(@AuthenticationPrincipal User user,
                       Model model) {
        model.addAttribute("user", user);
        return "article/list";
    }

    //method show the form for creating a new article
    @GetMapping("/article/create")
    public String showForm(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("user", user);
        return "article/form";
    }

    //method creates a new article and returns a list of all articles
    @PostMapping("/article/create")
    public String create(@AuthenticationPrincipal User user,
                         @ModelAttribute("article") @Valid Article article,
                         @RequestParam("file") MultipartFile image,
                         BindingResult result) {

        articleValidator.validate(article,result);
        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return "article/form";
        }
        imageService.saveImageFile(article,image);
        user.getArticles().add(article);
        article.setAuthor(user);
        articleService.save(article);
        userService.update(user);
        return "redirect:/user_articles";
    }

    //method show all information of article
    @GetMapping("article/read/{articleId}")
    public String showContent(@AuthenticationPrincipal User user,
                              @PathVariable("articleId") Article article,
                              Model model) {
        model.addAttribute("user",user);
        model.addAttribute("article", article);
        return "article/index";
    }

    //method delete article
    @GetMapping("article/delete/{articleId}")
    public String delete(@AuthenticationPrincipal User user,
                         @PathVariable("articleId") Article article) {
        Set<Article> userArticles = new HashSet<>(user.getArticles());
        userArticles.remove(article);
        user.setArticles(userArticles);
        userService.update(user);
        articleService.delete(article);

        return "redirect:/user_articles";
    }


}
