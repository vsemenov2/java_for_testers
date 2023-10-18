package manager;

import model.ContactDate;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {
    public  ContactHelper(ApplicationManager manager) { super(manager); }


    public void openContactHomePage() {
        if (!manager.isElementPresent(By.name("MainForm"))) {
            manager.driver.findElement(By.linkText("home")).click();
        }
    }

    public void openContactNewPage() {
        if (!manager.isElementPresent(By.name("submit"))) {
            manager.driver.findElement(By.linkText("add new")).click();
        }
    }


    public void createContact(ContactDate contact) {
        openContactNewPage();
        manager.driver.findElement(By.name("firstname")).sendKeys(contact.firstname());
        manager.driver.findElement(By.name("middlename")).sendKeys(contact.middlename());
        manager.driver.findElement(By.name("lastname")).sendKeys(contact.lastname());
        manager.driver.findElement(By.name("address")).sendKeys(contact.address());
        manager.driver.findElement(By.name("submit")).click();
    }

    public  void removeContacts() {
        openContactHomePage();
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
        //assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
        manager.driver.switchTo().alert().accept();
    }
    public  void removeContactSelectAll() {
        openContactHomePage();
        manager.driver.findElement(By.id("MassCB")).click();
        manager.driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
        //assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
        manager.driver.switchTo().alert().accept();
    }

    public int getCount() {
        openContactHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();

    }

    public void removeAllContact() {
        openContactHomePage();
        selectAllContacts();
        removeContacts();

    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }
}
