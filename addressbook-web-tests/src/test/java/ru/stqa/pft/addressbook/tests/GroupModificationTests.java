package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Michael on 23.01.2017.
 */
public class GroupModificationTests extends BaseTest {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "test8", null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupsList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().modifySelectedGroup();
        GroupData group = new GroupData(before.get(before.size()-1).getId(), "test1", "test8", null);
        app.getGroupHelper().fillGroupData(group);
        app.getGroupHelper().updateNewGroupData();
        app.getGroupHelper().returnGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupsList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);

        Comparator<? super GroupData> byId = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);

    }

}
