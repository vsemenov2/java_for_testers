package manager;

import model.ContactDate;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

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

    public  void removeContacts(ContactDate contact) {
        openContactHomePage();
        selectContact(contact);
        removeSelectedContacts();
        openContactHomePage();
    }

    private void removeSelectedContacts() {
        manager.driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
        //assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
        manager.driver.switchTo().alert().accept();
    }

    private void selectContact(ContactDate contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public  void removeContactSelectAll() {
        openContactHomePage();
        manager.driver.findElement(By.id("MassCB")).click();
        removeSelectedContacts();
        openContactHomePage();
    }

    public int getCount() {
        openContactHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();

    }

    public void removeAllContact() {
        openContactHomePage();
        selectAllContacts();
        removeSelectedContacts();

    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactDate> getList() {
        openContactHomePage();
        var contacts = new ArrayList<ContactDate>();
        //var trs = manager.driver.findElements(By.cssSelector("td.center"));
        var trs = manager.driver.findElements(By.xpath("//tr[@name=\'entry\']"));
        for (var tr : trs) {
            //var name = td.getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactDate().withId(id));

        }
        return contacts;
    }
}
