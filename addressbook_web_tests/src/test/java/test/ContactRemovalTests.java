package test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactRemovalTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost/addressbook/");
        driver.manage().window().setSize(new Dimension(1888, 999));
        driver.findElement(By.name("user")).click();
        driver.findElement(By.name("user")).sendKeys("admin");
        driver.findElement(By.name("pass")).click();
        driver.findElement(By.name("pass")).sendKeys("secret");
        driver.findElement(By.cssSelector("input:nth-child(7)")).click();
    }

    @AfterEach
    public void tearDown() {
        driver.findElement(By.linkText("Logout")).click();
        driver.quit();
    }

    @Test
    public void canRemoveContact() {
        if (!isElementPresent(By.name("Enter"))) {
            driver.findElement(By.linkText("add new")).click();
        }
        if (!isElementPresent(By.name("selected[]"))) {
            driver.findElement(By.linkText("add new")).click();
            driver.findElement(By.name("firstname")).sendKeys("");
            driver.findElement(By.name("middlename")).sendKeys("");
            driver.findElement(By.name("lastname")).sendKeys("");
            driver.findElement(By.name("address")).sendKeys("");
            driver.findElement(By.cssSelector("input:nth-child(87)")).click();

        }
        driver.findElement(By.linkText("home")).click();
        driver.findElement(By.name("selected[]")).click(); 
        driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
        //assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
        driver.switchTo().alert().accept();

    }
    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
}
