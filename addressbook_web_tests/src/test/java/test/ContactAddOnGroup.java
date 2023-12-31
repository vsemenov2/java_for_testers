package test;

import common.CommonFunctions;
import model.ContactDate;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class ContactAddOnGroup extends TestBase {

    @Test
    void canContactAddOnGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(
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

        var groupList = app.hbm().getGroupList();
        GroupData groupData = groupList.get(0);
        var oldContactListInGroup = app.hbm().getContactsInGroup(groupData);
        ContactDate contactAddGroup = null;

        var contactListNotInGroup = app.hbm().getContactsNotInGroup();
        if ((contactListNotInGroup != null) && (!contactListNotInGroup.isEmpty())) {
            contactAddGroup = contactListNotInGroup.get(0);
            app.contacts().addContactInToGroup(contactAddGroup, groupData);
        }

        var contact = new ContactDate()
                .withFirstname(CommonFunctions.randomString(5))
                .withMiddlename(CommonFunctions.randomString(5))
                .withLastname(CommonFunctions.randomString(5))
                .withNickname(CommonFunctions.randomString(5))
                .withCompany(CommonFunctions.randomString(5))
                .withTitle(CommonFunctions.randomString(5))
                .withAddress(CommonFunctions.randomString(5))
                .withHome(CommonFunctions.randomString(5));

            if (contactAddGroup == null) {
                app.contacts().createContact(contact);
                contact = contact.withId(app.hbm().getIdContactByName(contact.firstname()));
                app.contacts().addContactInToGroup(contact, groupData);
                contactAddGroup = contact;
            }

            var expectedContactListInGroup = app.hbm().getContactsInGroup(groupData);
            var newContactListInGroup = new ArrayList<>(oldContactListInGroup);
            newContactListInGroup.add(contactAddGroup);

            Comparator<ContactDate> compareById = (o1, o2) -> {
                return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
            };
            expectedContactListInGroup.sort(compareById);
            newContactListInGroup.sort(compareById);

            Assertions.assertEquals(expectedContactListInGroup, newContactListInGroup);
        }
    }


