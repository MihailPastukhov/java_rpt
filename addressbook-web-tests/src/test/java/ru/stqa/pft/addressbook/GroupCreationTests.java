package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends BaseTest {

    @Test
    public void testGroupCreation() {
        goToGroupPage();
        groupCreation();
        fillGroupData(new GroupData("test2", "test3", "test4"));
        submitGroupCreation();
        returnGroupPage();
    }

}
