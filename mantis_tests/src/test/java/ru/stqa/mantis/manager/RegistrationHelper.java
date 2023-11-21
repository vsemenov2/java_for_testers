package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import ru.stqa.mantis.model.UserRegistration;

public class RegistrationHelper extends HelperBase{
    public RegistrationHelper(ApplicationManager manager) {
        super(manager);
    }
    public void canCreateUser(UserRegistration registration) {
        pushLinkForRegistration();
        fillRegistrationForm(registration);
        clickSignup();

    }
    public void canConfirmUser(String realName, String password) {
        type(By.name("realname"), realName);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        updateUser();
    }

    private void clickSignup() {
        manager.driver().findElement(By.xpath("//input[@value='Signup']")).click();

    }

    private void pushLinkForRegistration() {
        manager.driver().findElement(By.xpath("//a[@href='signup_page.php']")).click();
    }

    private void fillRegistrationForm(UserRegistration registration) {
        type(By.name("username"), registration.username());
        type(By.name("email"), registration.email());
    }

    private void updateUser() {
        manager.driver().findElement(By.xpath("//button[@type='submit']")).click();
    }

    public void startCreation(String username, String email) {
        click(By.xpath("//*[@id='login-box']/div/div[2]/a"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
        click(By.xpath("//*[@id='login-box']/div/div/div[4]/a"));
    }

    public void finishCreation(String url, String username, String password) {
        manager.driver().get(url);
        type(By.name("realname"), username);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//*[@id=\"account-update-form\"]/fieldset/span/button\n"));
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }
}
