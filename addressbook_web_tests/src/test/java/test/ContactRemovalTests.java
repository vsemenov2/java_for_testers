package test;

import model.ContactDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(
                    new ContactDate("contact firstname", "contact middlename",
                            "contact lastname", "contact address"));
        }
        int contactCount = app.contacts().getCount();
        app.contacts().removeContacts();
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount - 1, newContactCount);

    }
     /*@Test
    public void canRemoveContactAll() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(
                    new ContactDate("contact firstname", "contact middlename",
                            "contact lastname", "contact address"));
        }
        app.contacts().removeContactSelectAll(); //удалить все контакты

    }*/

    @Test
    void  canRemoveAllContactsAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(
                    new ContactDate("contact firstname", "contact middlename",
                            "contact lastname", "contact address"));
        }
        app.contacts().removeAllContact();
        Assertions.assertEquals(1, app.contacts().getCount());
    }
}
