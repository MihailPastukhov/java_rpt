package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends BaseTest {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupsList();
        app.getGroupHelper().groupCreation();
        GroupData group = new GroupData("test5", "test6", "test7");
        app.getGroupHelper().fillGroupData(group);
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupsList();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.setId(after.stream().max(((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))).get().getId());
        before.add(group);

        Comparator<? super GroupData> byId = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
