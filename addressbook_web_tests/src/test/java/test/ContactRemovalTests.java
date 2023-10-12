package test;
import manager.ApplicationManager;
import model.ContactDate;
import org.junit.jupiter.api.Test;


public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemoveContact() {
        app.openContactHomePage();
        if (!app.isContactPresent()) {
            app.createContact(
                    new ContactDate("contact firstname", "contact middlename", "contact lastname", "contact address"));
        }
        app.removeContact();

    }

}
