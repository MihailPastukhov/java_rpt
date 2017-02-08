package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Michael on 23.01.2017.
 */
public class ContactModificationTests extends BaseTest {

    @Test(enabled = false)
    public void testContactModification(){

//        app.goTo().groupPage();
//        if (!app.group().isThereAGroup()){
//            app.group().create(new GroupData("test1", "test8", null));
//        }

        app.goTo().goToHomePage();
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("test1", "test2", "nickTest", "testAddress1, 123", "123123123", "321258952", "testEmail@email.com", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactsList();
        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().modifySelectedContact();
        ContactData contact = new ContactData("test313", "test2", null, "testaddress, 334", "258753159", "195375888", "testEmail@email.com", null);
        app.getContactHelper().fillContactData(contact, false);
        app.getContactHelper().updateSelectedContact();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-2);
        before.add(contact);

        Comparator<? super ContactData> byFirstName = ((o1, o2) -> (o1.getFirstName().compareTo(o2.getFirstName())));
        before.sort(byFirstName);
        after.sort(byFirstName);

        Assert.assertEquals(before, after);
    }

}
