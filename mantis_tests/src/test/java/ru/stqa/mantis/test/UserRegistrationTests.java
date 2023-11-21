package ru.stqa.mantis.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserRegistration;

import java.time.Duration;
import java.util.List;

public class UserRegistrationTests extends TestBase {


    public static List<UserRegistration> singleUser() {
        var name = CommonFunctions.randomString(10);
        return List.of(new UserRegistration()
                .withUsername(name)
                .withEmail(String.format("%s@localhost", name)));
    }

    @ParameterizedTest
    @MethodSource("singleUser")
    void canRegisterUser(UserRegistration registration) {
        app.jamesApi().addUser(registration.email(), "password");

        app.registration().canCreateUser(registration);

        var messages = app.mail().receive(registration.email(), "password", Duration.ofSeconds(10));
        var text = messages.get(0).content();
        var url = CommonFunctions.extract(text);
        app.driver().get(url);

        app.registration().canConfirmUser(CommonFunctions.randomString(10), "password");
        app.http().login(registration.username(), "password");
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @ParameterizedTest
    @MethodSource("singleUser")
    void canCreateUserRestApi(UserRegistration registration) {
        app.jamesApi().addUser(registration.email(), "password");
        app.rest().addUser(registration);
        var messages = app.mail().receive(registration.email(), "password", Duration.ofSeconds(60));
        var text = messages.get(0).content();
        var url = CommonFunctions.extract(text);

        app.driver().get(url);

        app.registration().canConfirmUser(registration.username(), "password");
        app.http().login(registration.username(), "password");
        Assertions.assertTrue(app.http().isLoggedIn());
    }

}
