package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends BaseTest {

    
    @Test
    public void testContactDeletion() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        if (!app.contact().isThereAContact()){
            app.contact().create(new ContactData().withFirstName("test313").withLastName("test2").withNickName(null).withAddress("testaddress, 334")
                    .withHomePhone("258753159").withMobile("195375888").withWork("195375888").withEmail("testEmail@email.com").withGroup("test1"), true);
        }
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);

        Assert.assertEquals(after, before);
    }





}
