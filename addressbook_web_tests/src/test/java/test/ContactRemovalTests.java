package test;

import model.ContactDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(
                    new ContactDate("", "contact firstname", "contact middlename",
                            "contact lastname", "", "", "", "contact address", "","","", "", "", "", "", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContacts(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);

    }


//    @Test
//    void  canRemoveAllContactsAtOnce() {
//        if (app.hbm().getContactCount() == 0) {
//            app.contacts().createContact(
//                    new ContactDate("", "contact firstname", "contact middlename",
//                            "contact lastname", "", "", "","contact address",  "","","", "", "", "", "", ""));
//        }
//        app.contacts().removeAllContact();
//        //Assertions.assertEquals(1, app.hbm().getContactCount());
//        Assertions.assertEquals(0, app.contacts().getCount());
//    }
}
