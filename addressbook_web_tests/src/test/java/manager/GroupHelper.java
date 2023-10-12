package manager;

import model.ContactDate;
import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager){
        super(manager);
    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }
    public void removeGroup() {
        openGroupsPage();
        selectGroup();
        removeSelectedGroup();
        returnToGroupsPage();
    }
    public void modifyGroup(GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup();
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }
    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }


    private void removeSelectedGroup() {
        click(By.name("delete"));
    }



    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void selectGroup() {
        click(By.name("selected[]"));
    }

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

    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactDate contact) {
        openContactNewPage();
        manager.driver.findElement(By.linkText("add new")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(contact.firstname());
        manager.driver.findElement(By.name("middlename")).sendKeys(contact.middlename());
        manager.driver.findElement(By.name("lastname")).sendKeys(contact.lastname());
        manager.driver.findElement(By.name("address")).sendKeys(contact.address());
        manager.driver.findElement(By.name("submit")).click();
    }

    public  void removeContact() {
        openContactNewPage();
        manager.driver.findElement(By.linkText("home")).click();
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
        //assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
        manager.driver.switchTo().alert().accept();
    }
}
