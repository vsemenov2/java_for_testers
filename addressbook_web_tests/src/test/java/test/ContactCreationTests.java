package test;

import model.ContactDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.ArrayList;
import java.util.List;


public class ContactCreationTests extends TestBase {

    public static List<ContactDate> contactProvider() {
        var result = new ArrayList<ContactDate>();
        for (var name : List.of("", "contact name")){
            for  (var middlename : List.of("", "contact middlename")){
                for  (var lastname : List.of("", "contact lastname")){
                    for  (var address : List.of("", "contact street")){
                        result.add(new ContactDate(name, middlename, lastname, address));
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            result.add(new ContactDate(randomString(i * 10 ), randomString(i * 10 ), randomString(i * 10 ), randomString(i * 10 )));

        }
        return result;
    }

 @ParameterizedTest
 @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactDate contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);

    }

    public static List<ContactDate> negativeContactProvider() {
        var result = new ArrayList<ContactDate>(List.of(
                new ContactDate("contact name'", "", "", "")));

        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactDate contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount, newContactCount);

    }


}
