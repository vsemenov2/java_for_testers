package test;

import model.ContactDate;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

    public static List<ContactDate> contactProvider() {
        var result = new ArrayList<ContactDate>();
        for (var name : List.of("", "contact name")){
            for  (var middlename : List.of("", "contact middlename")){
                for  (var lastname : List.of("", "contact lastname")){
                    for  (var address : List.of("", "contact street")) {
                        for (var photo : List.of("src/test/resources/images/avatar.png")) {
                            result.add(new ContactDate()
                                    .withFirstname(name)
                                    .withMiddlename(middlename)
                                    .withLastname(lastname)
                                    .withAddress(address)
                                    .withPhoto(photo));
                        }
                    }
                }
            }
        }

      for (int i = 0; i < 5; i++) {
            result.add(new ContactDate()
                    .withFirstname(randomString(i * 10 ))
                    .withMiddlename(randomString(i * 10 ))
                    .withLastname(randomString(i * 10 ))
                    .withAddress(randomString(i * 10 ))
                    .withPhoto(randomFile("src/test/resources/images")));

        }
        return result;
    }

 @ParameterizedTest
 @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactDate contact) {
       var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
     Comparator<ContactDate> compareById = (o1, o2) -> {
         return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
     };
     newContacts.sort(compareById);

     var expectedList = new ArrayList<>(oldContacts);
     expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id())
             .withFirstname(contact.firstname())
             .withMiddlename("")
             .withLastname(contact.lastname())
             .withAddress("")
             .withPhoto(""));
     expectedList.sort(compareById);
     Assertions.assertEquals(newContacts, expectedList);

    }

    public static List<ContactDate> negativeContactProvider() {
        var result = new ArrayList<ContactDate>(List.of(
                new ContactDate("", "contact name'", "", "", "","")));

        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactDate contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Assertions.assertEquals(newContacts, oldContacts);

    }


}
