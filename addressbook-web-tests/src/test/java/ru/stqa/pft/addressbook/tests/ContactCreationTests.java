package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends BaseTest {


    @Test
    public void testContactCreation()  {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();

        app.contact().addNewContact();

        ContactData contact = new ContactData().withFirstName("test313").withLastName("test2").withNickName(null).withAddress("testaddress, 334")
                .withHomePhone("258753159").withMobile("195375888").withWork("195375888").withEmail("testEmail@email.com").withGroup("test1");
        app.contact().fillContactData(contact, true);
        app.contact().submitContactCreation();
        app.contact().returnToHomePage();
        List<ContactData> after = app.contact().list();

        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(contact);

        Comparator<? super ContactData> byFirstName = ((o1, o2) -> (o1.getFirstName().compareTo(o2.getFirstName())));
        before.sort(byFirstName);
        after.sort(byFirstName);

        Assert.assertEquals(before, after);
    }

}
