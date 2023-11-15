package test;

import com.fasterxml.jackson.core.type.TypeReference;
import common.CommonFunctions;
import model.ContactDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
                .withEmail3(CommonFunctions.randomString(5))
                .withAddress2(CommonFunctions.randomString(5)));
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
             .withNickname("")
             .withCompany("")
             .withTitle("")
             .withAddress("")
             .withHome("")
             .withMobile("")
             .withWork("")
             .withEmail("")
             .withEmail2("")
             .withEmail3("")
             .withAddress2(""));



     expectedUIList.sort(compareByIdUI);
     Assertions.assertEquals(newUiContacts, expectedUIList);

    }

    public static List<ContactDate> negativeContactProvider() {
        var result = new ArrayList<ContactDate>(List.of(
                new ContactDate("", "contact name'", "", "", "","",
                        "","", "","", "", "", "", "", "", "")));

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
    @Test
    void canCreateContactInGroup() {
        var contact = new ContactDate()
                .withFirstname(CommonFunctions.randomString(5))
                .withMiddlename(CommonFunctions.randomString(5))
                .withLastname(CommonFunctions.randomString(5))
                .withNickname(CommonFunctions.randomString(5))
                .withCompany(CommonFunctions.randomString(5))
                .withTitle(CommonFunctions.randomString(5))
                .withAddress(CommonFunctions.randomString(5))
                .withHome(CommonFunctions.randomString(5));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContactInGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size()+1, newRelated.size());


    }


}
