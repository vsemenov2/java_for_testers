package test;

import common.CommonFunctions;
import model.ContactDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {


    @Test
    void testPhones() {
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
                            .withHome(CommonFunctions.randomString(5))
                            .withEmail(CommonFunctions.randomString(5))
                            .withEmail2(CommonFunctions.randomString(5))
                            .withEmail3(CommonFunctions.randomString(5)));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactDate::id, contact ->
                    Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                            .filter(s -> s != null && !"".equals(s))
                            .collect(Collectors.joining("\n"))
                ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones );

    }
    @Test
    void testEmails() {
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
                            .withHome(CommonFunctions.randomString(5))
                            .withEmail(CommonFunctions.randomString(5))
                            .withEmail2(CommonFunctions.randomString(5))
                            .withEmail3(CommonFunctions.randomString(5)));
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var emails = app.contacts().getEmails(contact);
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, emails);
    }

    @Test
    void testAddresses() {
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
                            .withHome(CommonFunctions.randomString(5))
                            .withEmail(CommonFunctions.randomString(5))
                            .withEmail2(CommonFunctions.randomString(5))
                            .withEmail3(CommonFunctions.randomString(5)));
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var addresses = app.contacts().getAddresses(contact);
        var expected = Stream.of(contact.address(), contact.address2())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, addresses);
    }
}
