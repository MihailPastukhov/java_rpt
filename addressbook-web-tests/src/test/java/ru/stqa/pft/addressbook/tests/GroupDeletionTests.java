package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends BaseTest {

    @BeforeMethod
    private void ensurePrecondition() {
        app.goTo().groupPage();
        if (app.group().list().size()==0){
            app.group().create(new GroupData("test1", "test8", null));
        }
    }

    @Test
    public void testGroupDeletion() {

        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(after, before);
    }


}
