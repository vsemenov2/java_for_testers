package test;

import com.fasterxml.jackson.core.type.TypeReference;
import common.CommonFunctions;
import model.ContactDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

    public static List<ContactDate> contactProvider() throws IOException {
        var result = new ArrayList<ContactDate>();
//        for (var name : List.of("", "contact name")){
//            for  (var middlename : List.of("", "contact middlename")){
//                for  (var lastname : List.of("", "contact lastname")){
//                    for  (var address : List.of("", "contact street")) {
//                        for (var photo : List.of(randomFile("src/test/resources/images"))) {
//                            result.add(new ContactDate()
//                                    .withFirstname(name)
//                                    .withMiddlename(middlename)
//                                    .withLastname(lastname)
//                                    .withAddress(address)
//                                    .withPhoto(photo));
//                        }
//                    }
//                }
//            }
//        }
        var json = "";
        try (var reader = new FileReader("contacts.json");
             var breader = new BufferedReader(reader)

        ){
            var line = breader.readLine();
            while (line != null){
                json = json + line;
                line = breader.readLine();
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactDate>>() {});
        result.addAll(value);
        return result;
    }

    public static List<ContactDate> singleRandomContact() {
        return List.of(new ContactDate()
                .withFirstname(CommonFunctions.randomString(10))
                .withMiddlename(CommonFunctions.randomString(20))
                .withLastname(CommonFunctions.randomString(30))
                .withAddress(CommonFunctions.randomString(40)));
    }

 @ParameterizedTest
 @MethodSource("singleRandomContact")
    public void canCreateContact(ContactDate contact) {
       var oldContacts = app.hbm().getContactList();
       var oldUiContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
     Comparator<ContactDate> compareById = (o1, o2) -> {
         return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
     };
     newContacts.sort(compareById);
     var maxId = newContacts.get(newContacts.size() - 1).id();

     var expectedList = new ArrayList<>(oldContacts);
     expectedList.add(contact.withId(maxId));
     expectedList.sort(compareById);
     Assertions.assertEquals(newContacts, expectedList);


     var newUiContacts = app.contacts().getList();
     Comparator<ContactDate> compareByIdUI = (o1, o2) -> {
         return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
     };
     newUiContacts.sort(compareByIdUI);
     var expectedUIList = new ArrayList<>(oldUiContacts);
     expectedUIList.add(contact.withId(newUiContacts.get(newUiContacts.size() - 1).id())
             .withFirstname(contact.firstname())
             .withMiddlename("")
             .withLastname(contact.lastname())
             .withAddress("")
             .withPhoto(""));

     expectedUIList.sort(compareByIdUI);
     Assertions.assertEquals(newUiContacts, expectedUIList);

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
