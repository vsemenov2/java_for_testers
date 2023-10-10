package test;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreatedGroup() {
        app.groups().createGroup(new GroupData("group name", "group header", "group footer") );

    }

    @Test
    public void canCreatedGroupWithEmptyName() {
        app.groups().createGroup(new GroupData() );

    }
    @Test
    public void canCreatedGroupWithEmptyNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));

    }
}




