package manager;

import model.ContactDate;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

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
        fillContactForm(contact);
        submitContactCreation();
        openContactHomePage();
    }

    public void createContactInGroup(ContactDate contact, GroupData group) {
        openContactNewPage();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        openContactHomePage();
    }

    private void selectGroup(GroupData group) {
       new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactDate contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
        type(By.name("address"), contact.address());
        type(By.name("nickname"), contact.nickname());
        type(By.name("title"), contact.title());
        type(By.name("company"), contact.company());
        type(By.name("home"), contact.home());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work());
        //attach(By.name("photo"), contact.photo());
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
        //manager.driver.findElement(By.cssSelector("div.msgbox"));
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
        var trs = manager.driver.findElements(By.xpath("//tr[@name=\'entry\']"));
        for (var tr : trs) {
            var nameLast = tr.findElement(By.xpath(".//td[2]"));
            var nameFirst = tr.findElement(By.xpath(".//td[3]"));
            var getLastname = nameLast.getText();
            var getFirstname = nameFirst.getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactDate()
                    .withId(id)
                    .withLastname(getLastname)
                    .withFirstname(getFirstname));

        }
        return contacts;
    }

    public void modifyContact(ContactDate contact, ContactDate modifiedContact) {
        openContactHomePage();
        selectContact(contact);
        initContactModification(contact);
        fillContactUpdateForm(modifiedContact);
        submitContactModification();
        openContactHomePage();

    }

    private void fillContactUpdateForm(ContactDate contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
        type(By.name("address"), contact.address());
        type(By.name("nickname"), contact.nickname());
        type(By.name("title"), contact.title());
        type(By.name("company"), contact.company());
        type(By.name("home"), contact.home());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work());

    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification(ContactDate contact) {
        click(By.cssSelector(String.format("a[href=\'edit.php?id=%s\']", contact.id())));
    }

    public void addContactInToGroup(ContactDate contact, GroupData group) {
        openContactHomePage();
        selectContact(contact);
        selectToGroup(group);
        addToContactInGroup();
    }
    private void selectToGroup(GroupData groupData) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(groupData.id());
    }
    private void addToContactInGroup() {
        manager.driver.findElement(By.name("add")).click();
    }

    public void removeContactFromGroup(ContactDate contact) {
        openContactHomePage();
//        selectGroupById(group);
        selectContact(contact);
            manager.driver.findElement(By.name("remove")).click();
        }

    public void selectGroupById(GroupData group) {
        openContactHomePage();
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public Object getPhones(ContactDate contact) {
        return manager.driver.findElement(
                By.xpath(String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }
}
