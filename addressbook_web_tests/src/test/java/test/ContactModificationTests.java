package test;

import model.ContactDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase{

    @Test
    void canModifyContact(){
        if (app.hbm().getContactCount() == 0){
            app.contacts().createContact(new ContactDate("",
                    "contact firstname",
                    "contact middlename",
                    "contact lastname",
                    "",
                    "",
                    "",
                    "contact address",
                    "",
                    "",
                    "", "", "", "", "", ""));

        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactDate().withFirstname("modified firstname");

        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id())
                .withFirstname(testData.firstname())
                .withLastname(testData.lastname()));


        Comparator<ContactDate> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
