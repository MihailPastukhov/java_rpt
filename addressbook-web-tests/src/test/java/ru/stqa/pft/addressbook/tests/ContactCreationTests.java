package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends BaseTest {


    @Test
    public void testContactCreation()  {
        app.goTo().homePage();
        Contacts before = app.contact().all();

        app.contact().addNewContact();

        ContactData contact = new ContactData().withFirstName("test313").withLastName("test2").withNickName(null).withAddress("testaddress, 334")
                .withHomePhone("258753159").withMobile("195375888").withWork("195375888").withEmail("testEmail@email.com").withGroup("test1");
        app.contact().create(contact, true);

        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
