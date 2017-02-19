package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Michael on 19.02.2017.
 */
public class ContactPhonesTest extends BaseTest {

    @Test
    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getHomePhone(), equalTo(cleared(contactInfoFromEditForm.getHomePhone())));
        assertThat(contact.getMobile(), equalTo(cleared(contactInfoFromEditForm.getMobile())));
        assertThat(contact.getWork(), equalTo(cleared(contactInfoFromEditForm.getWork())));
    }

    public String cleared(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }


}
