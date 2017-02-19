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

    @Test
    public void testContactModification(){

//        app.goTo().groupPage();
//        if (!app.group().isThereAGroup()){
//            app.group().create(new GroupData("test1", "test8", null));
//        }

        app.goTo().homePage();
        if (!app.contact().isThereAContact()){
            app.contact().create(new ContactData().withFirstName("test313").withLastName("test2").withNickName(null).withAddress("testaddress, 334")
                    .withHomePhone("258753159").withMobile("195375888").withWork("195375888").withEmail("testEmail@email.com").withGroup("test1"), true);
        }
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        ContactData contact = new ContactData().withFirstName("test313").withLastName("test2").withNickName(null).withAddress("testaddress, 334")
                .withHomePhone("258753159").withMobile("195375888").withWork("2020327").withEmail("testEmail@email.com").withGroup(null);
        app.contact().modify(contact, index);

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);

        Comparator<? super ContactData> byFirstName = ((o1, o2) -> (o1.getFirstName().compareTo(o2.getFirstName())));
        before.sort(byFirstName);
        after.sort(byFirstName);

        Assert.assertEquals(before, after);
    }

}
