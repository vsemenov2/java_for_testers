package ru.stqa.mantis.manager;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private String string;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;


    public void init(String browser, Properties properties) {
        this.string = browser;
        this.properties = properties;
           }

    public WebDriver driver() {
        if (driver == null){
            if ("firefox".equals(string)){
                driver = new FirefoxDriver();
            } else if ("chrome".equals(string)){
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", string));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1920, 1040));

        }
        return driver;
    }

    public SessionHelper session(){
        if(sessionHelper == null){
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }


    public HttpSessionHelper http() {
        if (httpSessionHelper == null) {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public String property(String name){
        return properties.getProperty(name);
    }

}
