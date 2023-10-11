package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactCreationTests extends TestBase {
    private static WebDriver driver;

    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1888, 999));
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.name("pass")).sendKeys(Keys.ENTER);
        }
    }


    @Test
    public void canCreateContact() {
        if (!isElementPresent(By.name("firstname"))) {
            driver.findElement(By.linkText("add new")).click();
        }
        driver.findElement(By.linkText("add new")).click();
        driver.findElement(By.name("firstname")).sendKeys("name");
        driver.findElement(By.name("middlename")).sendKeys("middlename");
        driver.findElement(By.name("lastname")).sendKeys("lastname");
        driver.findElement(By.name("address")).sendKeys("address");
        driver.findElement(By.cssSelector("input:nth-child(87)")).click();


    }

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    @Test
    public void canCreateContactWithEmptyName() {
        if (!isElementPresent(By.name("Enter"))) {
            driver.findElement(By.linkText("add new")).click();
        }
        driver.findElement(By.linkText("add new")).click();
        driver.findElement(By.name("firstname")).sendKeys("");
        driver.findElement(By.name("middlename")).sendKeys("");
        driver.findElement(By.name("lastname")).sendKeys("");
        driver.findElement(By.name("address")).sendKeys("");
        driver.findElement(By.cssSelector("input:nth-child(87)")).click();


    }

}
