package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

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
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().modifySelectedGroup();
        app.getGroupHelper().fillGroupData(new GroupData("test1", "test8", null));
        app.getGroupHelper().updateNewGroupData();
        app.getGroupHelper().returnGroupPage();


    }

}
