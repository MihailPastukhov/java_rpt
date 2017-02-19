package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Michael on 20.01.2017.
 */
public class ContactHelper extends BaseHelper {


    private boolean thereAContact;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void addNewContact() {
        click(By.xpath(".//a[contains(text(),'add new')]"));
    }

    public void fillContactData(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());
        type(By.name("email"), contactData.getEmail());
        if(creation){
            if(isElementPresent(By.name("new_group"))){
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }



    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void selectContact(int index) {
        wd.findElements(By.xpath("//input[@name='selected[]']")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.xpath("//input[@value='"+ id +"']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void modifySelectedContact() {
        click(By.cssSelector("img[alt=Edit]"));
    }

    public void updateSelectedContact() {
        click(By.name("update"));
    }

    public void returnToHomePage() {
        click(By.xpath("//a[contains(text(),'home')]"));
    }

    public void create(ContactData contact, boolean creation) {
        addNewContact();
        fillContactData(new ContactData().withFirstName(contact.getFirstName()).withLastName(contact.getLastName()).withNickName(contact.getNickName())
                .withAddress(contact.getAddress()).withHomePhone(contact.getHomePhone()).withMobile(contact.getMobile()).withWork(contact.getWork())
                .withEmail(contact.getEmail()).withGroup(contact.getGroup()), creation);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact){
        selectContactById(contact.getId());
        modifySelectedContact();
        fillContactData(new ContactData().withFirstName(contact.getFirstName()).withLastName(contact.getLastName()).withNickName(contact.getNickName())
                .withAddress(contact.getAddress()).withHomePhone(contact.getHomePhone()).withMobile(contact.getMobile()).withWork(contact.getWork())
                .withEmail(contact.getEmail()).withGroup(contact.getGroup()), false);
        updateSelectedContact();
        contactCache = null;
        returnToHomePage();
    }


    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactsCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr[@name='entry']"));
        for (int i=1 ; i < elements.size()+1 ; i++){
            String lastName = wd.findElement(By.xpath("//tr[@name='entry']["+i+"]/td[2]")).getText();
            String firstName = wd.findElement(By.xpath("//tr[@name='entry']["+i+"]/td[3]")).getText();
            String address = wd.findElement(By.xpath("//tr[@name='entry']["+i+"]/td[4]")).getText();
            String email = wd.findElement(By.xpath("//tr[@name='entry']["+i+"]/td[5]")).getText();
            String phones = wd.findElement(By.xpath("//tr[@name='entry']["+i+"]/td[6]")).getText();
            List<String> phonesList = new ArrayList<String>();
            for (String phone : phones.split("\n")) {
                phonesList.add(phone);
            }
            String homePhone = new String();
            String mobile = new String();
            String work = new String();
            switch (phonesList.size()){
                case 1:
                    homePhone = phonesList.get(0);
                    break;
                case 2:
                    homePhone = phonesList.get(0);
                    mobile = phonesList.get(1);
                    break;
                case 3:
                    homePhone = phonesList.get(0);
                    mobile = phonesList.get(1);
                    work = phonesList.get(2);
                    break;
            }
            phonesList.clear();

            ContactData contact = new ContactData().withFirstName(firstName).withLastName(lastName).withNickName(null).withAddress(address)
                    .withHomePhone(homePhone).withMobile(mobile).withWork(work).withEmail(email).withGroup(null);
            contacts.add(contact);
        }
        return contacts;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr[@name='entry']"));
        for (int i=1 ; i < elements.size()+1 ; i++){
            int id = Integer.parseInt(wd.findElement(By.xpath("//tr[@name='entry']["+i+"]/td[1]/input")).getAttribute("value"));
            String lastName = wd.findElement(By.xpath("//tr[@name='entry']["+i+"]/td[2]")).getText();
            String firstName = wd.findElement(By.xpath("//tr[@name='entry']["+i+"]/td[3]")).getText();
            String address = wd.findElement(By.xpath("//tr[@name='entry']["+i+"]/td[4]")).getText();
            String email = wd.findElement(By.xpath("//tr[@name='entry']["+i+"]/td[5]")).getText();
            String phones = wd.findElement(By.xpath("//tr[@name='entry']["+i+"]/td[6]")).getText();
            List<String> phonesList = new ArrayList<String>();
            for (String phone : phones.split("\n")) {
                phonesList.add(phone);
            }
            String homePhone = new String();
            String mobile = new String();
            String work = new String();
            switch (phonesList.size()){
                case 1:
                    homePhone = phonesList.get(0);
                    break;
                case 2:
                    homePhone = phonesList.get(0);
                    mobile = phonesList.get(1);
                    break;
                case 3:
                    homePhone = phonesList.get(0);
                    mobile = phonesList.get(1);
                    work = phonesList.get(2);
                    break;
            }
            phonesList.clear();

            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withNickName(null).withAddress(address)
                    .withHomePhone(homePhone).withMobile(mobile).withWork(work).withEmail(email).withGroup(null);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }




    public String getPhone() {
        return wd.findElement(By.xpath("//tr[@name='entry']/td[6]")).getText();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName).withHomePhone(home).withMobile(mobile).withWork(work);
    }

    public void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.xpath(String.format("//input[@value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }
}
