package com.questionnaire.util.validation;

import com.questionnaire.area.user.dto.bind.UserRegistrationBind;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ChaosFire on 15.4.2017 г
 */
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    @Override
    public void initialize(PasswordMatch passwordMatch) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        boolean isMatching = false;
        if (object instanceof UserRegistrationBind) {
            UserRegistrationBind userBind = (UserRegistrationBind) object;
            isMatching = userBind.getPassword().equals(userBind.getConfirmPassword());
        }
        return isMatching;
    }
}