package com.example.articlestore.validator;

import com.example.articlestore.domain.Article;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ArticleValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Article.class.equals(aClass);
    }

    //checks article form
    @Override
    public void validate(Object target, Errors errors) {
        Article article = (Article) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "EmptyOrWhiteSpace");
        if (article.getTitle().length() < 4 || article.getTitle().length() > 32) {
            errors.rejectValue("title", "Size.title");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "EmptyOrWhiteSpace");
        if (article.getContent().length() < 12) {
            errors.rejectValue("content", "Size.content");
        }
    }
}
