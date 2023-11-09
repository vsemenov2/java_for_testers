package test;

import common.CommonFunctions;
import model.ContactDate;
import model.GroupData;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ContactAddOnGroup extends TestBase{

@Test
    void canContactAddOnGroup() {
    if (app.hbm().getGroupCount() == 0) {
        app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
    }
    if (app.hbm().getContactCount() == 0) {
        app.contacts().createContact(
                new ContactDate()
                        .withFirstname(CommonFunctions.randomString(5))
                        .withMiddlename(CommonFunctions.randomString(5))
                        .withLastname(CommonFunctions.randomString(5))
                        .withNickname(CommonFunctions.randomString(5))
                        .withCompany(CommonFunctions.randomString(5))
                        .withTitle(CommonFunctions.randomString(5))
                        .withAddress(CommonFunctions.randomString(5))
                        .withHome(CommonFunctions.randomString(5)));
    }
    var group = app.hbm().getGroupList();
    var contact = app.hbm().getContactList();
    var rnd = new Random();
    var indexGroup = rnd.nextInt(group.size());
    var indexContact = rnd.nextInt(contact.size());
    app.contacts().addContactInToGroup(contact.get(indexContact), group.get(indexGroup));

}

}
