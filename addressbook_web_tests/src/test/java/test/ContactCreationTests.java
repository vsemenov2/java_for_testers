package test;

import model.ContactDate;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.groups().createContact(
                new ContactDate("contact firstname", "contact middlename",
                        "contact lastname", "contact address"));

    }

    @Test
    public void canCreateContactWithEmptyName() {
        app.groups().createContact(new ContactDate());

    }

    @Test
    public void canCreateContactWithEmptyFirstnameOnly() {
        var emptyContact = new ContactDate();
        var contactWithFirstname = emptyContact.withFirstname("some name");
        app.groups().createContact(contactWithFirstname);

    }

}
