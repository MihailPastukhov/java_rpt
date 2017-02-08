package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends BaseTest {


    @Test
    public void testContactCreation()  {
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactsList();

        app.getContactHelper().addNewContact();

        ContactData contact = new ContactData("test313", "test2", null, "testaddress, 334", "258753159", "195375888", "testEmail@email.com", "test1");
        app.getContactHelper().fillContactData(contact, true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactsList();

        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(contact);

        Comparator<? super ContactData> byFirstName = ((o1, o2) -> (o1.getFirstName().compareTo(o2.getFirstName())));
        before.sort(byFirstName);
        after.sort(byFirstName);

        Assert.assertEquals(before, after);
    }

}
