package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends BaseTest {


    @Test
    public void testContactCreation()  {
        app.getContactHelper().addNewContact();
        app.getContactHelper().fillContactData(new ContactData("test1", "test2", "nickTest", "testAddress1, 123", "123-123-123", "321-258-952", "testEmail@email.com", "test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }

}
