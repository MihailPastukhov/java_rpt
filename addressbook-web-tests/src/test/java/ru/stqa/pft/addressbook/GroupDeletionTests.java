package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends BaseTest {

    
    @Test
    public void testGroupDeletion() {
        goToGroupPage();
        selectGroup();
        deleteSelectedGroups();
        returnGroupPage();
    }


}
