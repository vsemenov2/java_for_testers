package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;

    public void init() {
       if (driver == null) {
           /* if ("firefox".equals(browser)){
                driver = new FirefoxDriver();
            } else if ("chrome".equals(browser)){
                driver = new ChromeDriver();

            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }*/
            driver = new ChromeDriver();
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

}