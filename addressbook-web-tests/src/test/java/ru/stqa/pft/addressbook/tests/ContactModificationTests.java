package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Michael on 23.01.2017.
 */
public class ContactModificationTests extends BaseTest {

    @Test
    public void testContactModification(){

        app.goTo().homePage();
        if (!app.contact().isThereAContact()){
            app.contact().create(new ContactData().withFirstName("test313").withLastName("test2").withNickName(null).withAddress("testaddress, 334")
                    .withHomePhone("258753159").withMobile("195375888").withWork("195375888").withEmail("testEmail@email.com").withGroup("test1"), true);
        }
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();

        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("test313").withLastName("test2").withNickName(null).withAddress("testaddress, 334")
                .withHomePhone("258753159").withMobile("195375888").withWork("2020327").withEmail("testEmail@email.com").withGroup(null);
        app.contact().modify(modifiedContact);

        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
