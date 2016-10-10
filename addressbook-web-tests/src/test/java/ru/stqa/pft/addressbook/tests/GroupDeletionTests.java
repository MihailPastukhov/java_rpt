package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends BaseTest {

    
    @Test
    public void testGroupDeletion() {
        app.goToGroupPage();
        app.selectGroup();
        app.deleteSelectedGroups();
        app.returnGroupPage();
    }


}
