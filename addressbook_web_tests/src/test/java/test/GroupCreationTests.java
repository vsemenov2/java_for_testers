package test;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreatedGroup() {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData("group name", "group header", "group footer") );
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1,newGroupCount );
    }

    @Test
    public void canCreatedGroupWithEmptyName() {
        app.groups().createGroup(new GroupData() );

    }
    @Test
    public void canCreatedGroupWithEmptyNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));

    }

    @Test
    public void canCreateMultipledGroups() {
        int n = 5;
        int groupCount = app.groups().getCount();

        for(int i = 0; i < n; i++) {
            app.groups().createGroup(new GroupData(randomString(i * 10), "group header ", "group footer "));
        }

        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + n,newGroupCount );
    }
}




