package ru.stqa.mantis.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.model.DeveloperMailUser;
import ru.stqa.mantis.model.UserRegistration;

import java.time.Duration;


public class UserCreationTest extends TestBase {

    DeveloperMailUser user;

    @Test
    void canCreateUser() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

        app.user().startCreation(user.name(), email);

        var message = app.developerMail().receive(user, Duration.ofSeconds(10));
        var url = message;

       app.driver().get((String) url);

        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
    @AfterEach
    void deleteMailUser(){

        app.developerMail().deleteUser(user);
    }



}
