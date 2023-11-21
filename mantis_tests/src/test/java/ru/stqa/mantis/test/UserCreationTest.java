package ru.stqa.mantis.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.model.DeveloperMailUser;
import ru.stqa.mantis.model.UserRegistration;


public class UserCreationTest extends TestBase {

    DeveloperMailUser user;

    @Test
    void canCreateUser() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

//        app.registration().canCreateUser(registration);
//
//        var messages = app.mail().receive(registration.email(), "password", Duration.ofSeconds(10));
//        var text = messages.get(0).content();
//        var pattern = Pattern.compile("http://\\S*");
//        var matcher = pattern.matcher(text);
//        String url = null;
//        if (matcher.find()) {
//            url = text.substring(matcher.start(), matcher.end());
//        }
//        app.driver().get(url);
//
//        app.registration().canConfirmUser(CommonFunctions.randomString(10), "password");
//        app.http().login(registration.username(), "password");
//        Assertions.assertTrue(app.http().isLoggedIn());
    }
    @AfterEach
    void deleteMailUser(){

        app.developerMail().deleteUser(user);
    }



}
