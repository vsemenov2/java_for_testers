package ru.stqa.mantis.test;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        //coздать пользователя на почтовом сервере (JamesHelper)
        // заполняем форму создания и отправка (браузер)
        // ждем почту (MailHelper)
        // извлекаем ссылку из письма
        // проходим по ссылке и завершаем регистрацию (браузер)
        // проверяем  (HttpSessionHelper)
    }
}
