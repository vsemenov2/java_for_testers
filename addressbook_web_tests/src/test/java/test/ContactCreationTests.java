package test;

import model.ContactDate;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.contacts().createContact(
                new ContactDate("contact firstname", "contact middlename",
                        "contact lastname", "contact address"));

    }

    @Test
    public void canCreateContactWithEmptyName() {
        app.contacts().createContact(new ContactDate());

    }

    @Test
    public void canCreateContactWithEmptyFirstnameOnly() {
        var emptyContact = new ContactDate();
        var contactWithFirstname = emptyContact.withFirstname("first name");
        app.contacts().createContact(contactWithFirstname);

    }
    @Test
    public void canCreateContactWithEmptyMiddlenameOnly() {
        var emptyContact = new ContactDate();
        var contactWithMiddlename = emptyContact.withMiddlename("middle name");
        app.contacts().createContact(contactWithMiddlename);

    }
    @Test
    public void canCreateContactWithEmptyLastnameOnly() {
        var emptyContact = new ContactDate();
        var contactWithLastname = emptyContact.withLastname("last name");
        app.contacts().createContact(contactWithLastname);

    }

    @Test
    public void canCreateContactWithEmptyAddressOnly() {
        var emptyContact = new ContactDate();
        var contactWithAddress = emptyContact.withAddress("street");
        app.contacts().createContact(contactWithAddress);

    }

}
