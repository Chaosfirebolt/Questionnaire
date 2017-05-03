package com.questionnaire.util.validation.account;

import com.questionnaire.area.user.entity.AbstractUser;
import com.questionnaire.exception.account.ExpiredAccountException;
import com.questionnaire.exception.account.InvalidAccountException;
import com.questionnaire.util.constants.error.LoginError;

/**
 * Created by ChaosFire on 21.4.2017 г
 */
public class AccountValidation {

    public static void check(AbstractUser user) throws InvalidAccountException {
        if (!user.isAccountNonExpired()) {
            throw new ExpiredAccountException(LoginError.EXPIRED_ACCOUNT);
        }

        if (!user.isAccountNonLocked()) {
            throw new ExpiredAccountException(LoginError.LOCKED_ACCOUNT);
        }

        if (!user.isCredentialsNonExpired()) {
            throw new ExpiredAccountException(LoginError.EXPIRED_CREDENTIALS);
        }

        if (!user.isEnabled()) {
            throw new ExpiredAccountException(LoginError.NOT_ENABLED_ACCOUNT);
        }
    }
}