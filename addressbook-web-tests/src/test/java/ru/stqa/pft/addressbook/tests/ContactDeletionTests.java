package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends BaseTest {

    
    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("test1", "test2", "nickTest", "testAddress1, 123", "123-123-123", "321-258-952", "testEmail@email.com", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().goToHomePage();
    }




}
