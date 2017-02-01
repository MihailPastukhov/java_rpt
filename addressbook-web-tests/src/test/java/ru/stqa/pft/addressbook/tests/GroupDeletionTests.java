package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends BaseTest {

    
    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "test8", null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupsList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupsList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        Assert.assertEquals(after, before);
    }


}
