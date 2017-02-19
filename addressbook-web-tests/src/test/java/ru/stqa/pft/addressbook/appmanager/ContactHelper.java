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
        returnToHomePage();
    }

    public void delete(int index) {
        selectContact(index);
        deleteSelectedContact();
        returnToHomePage();
    }

    public void modify(ContactData contact, int index){
        selectContact(index);
        modifySelectedContact();
        fillContactData(new ContactData().withFirstName(contact.getFirstName()).withLastName(contact.getLastName()).withNickName(contact.getNickName())
                .withAddress(contact.getAddress()).withHomePhone(contact.getHomePhone()).withMobile(contact.getMobile()).withWork(contact.getWork())
                .withEmail(contact.getEmail()).withGroup(contact.getGroup()), false);
        updateSelectedContact();
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

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<>();
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




    public String getPhone() {
        return wd.findElement(By.xpath("//tr[@name='entry']/td[6]")).getText();
    }
}
