package com.example.articlestore.validator;

import com.example.articlestore.domain.User;
import com.example.articlestore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class UserProfileValidator implements Validator {

    private final UserService userService;

    public UserProfileValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    //checks user profile form
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "EmptyOrWhiteSpace");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "EmptyOrWhiteSpace");
        if (user.getFirstName().length() < 6 || user.getFirstName().length() > 32) {
            errors.rejectValue("firstName", "Size.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "EmptyOrWhiteSpace");
        if (user.getLastName().length() < 6 || user.getLastName().length() > 32) {
            errors.rejectValue("lastName", "Size.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "EmptyOrWhiteSpace");
        if (user.getLastName().length() < 6) {
            errors.rejectValue("address", "Size.address");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.username");
        }

        if (user.getPassword() != null && !user.getPassword().isEmpty() && !user.getPassword().equals("")) {
            if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
                errors.rejectValue("password", "Size.password");
            }
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "EmptyOrWhiteSpace");
            if (!user.getPassword().equals(user.getPasswordConfirm())) {
                errors.rejectValue("passwordConfirm", "ConfirmPassword");
            }
        }


    }
}
