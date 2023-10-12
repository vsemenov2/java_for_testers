package manager;

import model.ContactDate;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import test.TestBase;

public class ApplicationManager  {
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;

    public  void removeContact() {
        driver.findElement(By.linkText("home")).click();
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
        //assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
        driver.switchTo().alert().accept();
    }

    public void init(String browser) {
       if (driver == null) {
            if ("firefox".equals(browser)){
                driver = new FirefoxDriver();
            } else if ("chrome".equals(browser)){
                driver = new ChromeDriver();

            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1920, 1040));
           session().login("admin", "secret", this);
       }
    }

    public LoginHelper session(){
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;
    }

    public GroupHelper groups() {
       if (groups == null){
           groups = new GroupHelper(this);
       }
       return groups;
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public void createContact(ContactDate contact) {
        driver.findElement(By.linkText("add new")).click();
        driver.findElement(By.name("firstname")).sendKeys(contact.firstname());
        driver.findElement(By.name("middlename")).sendKeys(contact.middlename());
        driver.findElement(By.name("lastname")).sendKeys(contact.lastname());
        driver.findElement(By.name("address")).sendKeys(contact.address());
        driver.findElement(By.name("submit")).click();
    }

    public void openContactNewPage() {
        if (!isElementPresent(By.name("submit"))) {
            driver.findElement(By.linkText("add new")).click();
        }
    }

    public void openContactHomePage() {
        if (!isElementPresent(By.name("MainForm"))) {
            driver.findElement(By.linkText("home")).click();
        }
    }

    public boolean isContactPresent() {
        return isElementPresent(By.name("selected[]"));
    }

}
