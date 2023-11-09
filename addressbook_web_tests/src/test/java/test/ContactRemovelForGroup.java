package test;

import common.CommonFunctions;
import model.ContactDate;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class ContactRemovelForGroup extends TestBase{

@Test
    void canContactRemovalForGroup(){
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
    ContactDate contact = null;
    GroupData group = null;

    if (contact == null) {
        var contactListNotGroup = app.hbm().getContactList();
        if ((contactListNotGroup != null) && (!contactListNotGroup.isEmpty())) {
            var groupList = app.hbm().getGroupList();
            contact = contactListNotGroup.get(0);
            group = groupList.get(0);
            app.contacts().addContactInToGroup(contact, group);
        }
    }
        var groupList = app.hbm().getGroupList();

        for (int i = 0; i < groupList.size() - 1; i++) {
            var contactListInGroup = app.hbm().getContactsInGroup(groupList.get(i));
            if ((contactListInGroup != null) && (!contactListInGroup.isEmpty())) {
                contact = contactListInGroup.get(0);
                group = groupList.get(i);

            }
        }
            var oldContacts = app.hbm().getContactsInGroup(group);
            app.contacts().selectGroupById(group);
            app.contacts().removeContactFromGroup(contact);
            var newContacts = app.hbm().getContactsInGroup(group);
            var expectedList = new ArrayList<>(oldContacts);

            ContactDate finalContactForDelete = contact;
            expectedList.removeIf(contactData -> finalContactForDelete.id().equals(contactData.id()));

            Comparator<ContactDate> compareById = (o1, o2) -> {
                return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
            };
            expectedList.sort(compareById);
            newContacts.sort(compareById);

            Assertions.assertEquals(expectedList, newContacts);
        }

    }



