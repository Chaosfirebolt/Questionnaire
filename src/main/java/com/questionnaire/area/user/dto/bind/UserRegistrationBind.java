package com.questionnaire.area.user.dto.bind;

import com.questionnaire.util.constants.error.RegistrationError;
import com.questionnaire.util.constants.Regex;
import com.questionnaire.util.validation.PasswordMatch;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by ChaosFire on 15.4.2017 г
 */
@PasswordMatch
public class UserRegistrationBind implements Serializable {

    @Pattern(regexp = Regex.USERNAME, message = RegistrationError.INVALID_USERNAME)
    private String username;
    @Pattern(regexp = Regex.EMAIL, message = RegistrationError.INVALID_EMAIL)
    private String email;
    @Pattern(regexp = Regex.PASSWORD, message = RegistrationError.INVALID_PASSWORD)
    private String password;
    private String confirmPassword;

    public UserRegistrationBind() {
        super();
    }

    public UserRegistrationBind(String username, String email, String password, String confirmPassword) {
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
        this.setConfirmPassword(confirmPassword);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}