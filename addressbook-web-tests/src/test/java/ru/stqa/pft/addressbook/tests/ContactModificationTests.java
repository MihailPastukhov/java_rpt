package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Michael on 23.01.2017.
 */
public class ContactModificationTests extends BaseTest {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "test8", null));
        }

        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("test1", "test2", "nickTest", "testAddress1, 123", "123-123-123", "321-258-952", "testEmail@email.com", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().modifySelectedContact();
        app.getContactHelper().fillContactData(new ContactData("newTest1", "newTest2", "newNickTest", "newTestAddress1, 123", "123-123-123", "321-258-952", "newTestEmail@email.com", null), false);
        app.getContactHelper().updateSelectedContact();
        app.getContactHelper().returnToHomePage();
    }

}
