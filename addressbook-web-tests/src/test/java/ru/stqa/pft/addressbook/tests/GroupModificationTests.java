package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by Michael on 23.01.2017.
 */
public class GroupModificationTests extends BaseTest {

    @BeforeMethod
    private void ensurePrecondition() {
        app.goTo().groupPage();
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification(){
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1").withHeader("test8").withFooter("test0");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);


        Assert.assertEquals(after, before);

    }


}
