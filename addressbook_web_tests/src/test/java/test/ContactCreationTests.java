package test;

import model.ContactDate;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.openContactNewPage();
        app.createContact(
                new ContactDate("contact firstname", "contact middlename", "contact lastname", "contact address"));

    }

    @Test
    public void canCreateContactWithEmptyName() {
        app.openContactNewPage();
        app.createContact(new ContactDate());

    }
    @Test
    public void canCreateContactWithEmptyFirstnameOnly() {
        app.openContactNewPage();
        var emtyContact = new ContactDate();
        var contactWithFirstname = emtyContact.withFirstname("some name");
        app.createContact(contactWithFirstname);

    }

    }
