package test;

import model.ContactDate;
import org.junit.jupiter.api.Test;


public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(
                    new ContactDate("contact firstname", "contact middlename",
                            "contact lastname", "contact address"));
        }
        app.contacts().removeContact();

    }
    @Test
    public void canRemoveContactAll() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(
                    new ContactDate("contact firstname", "contact middlename",
                            "contact lastname", "contact address"));
        }
        app.contacts().removeContactAll(); //удалить все контакты

    }
}
