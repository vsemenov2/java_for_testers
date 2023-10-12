package test;

import model.ContactDate;
import org.junit.jupiter.api.Test;


public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.groups().isContactPresent()) {
            app.groups().createContact(
                    new ContactDate("contact firstname", "contact middlename",
                            "contact lastname", "contact address"));
        }
        app.groups().removeContact();

    }

}
