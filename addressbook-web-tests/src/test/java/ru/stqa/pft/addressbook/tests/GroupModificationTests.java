package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Administrator on 10/12/2016.
 */
public class GroupModificationTests extends BaseTest {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupData(new GroupData("test2", "test3", "test4"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnGroupPage();


    }
}
