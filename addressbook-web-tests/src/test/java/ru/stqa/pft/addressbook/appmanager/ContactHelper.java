package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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
        click(By.xpath("//a[contains(text(),'home page')]"));
    }

    public void createContact(ContactData contactData, boolean creation) {
        addNewContact();
        fillContactData(new ContactData("test1", "test2", "nickTest", "testAddress1, 123", "123-123-123", "321-258-952", "testEmail@email.com", "test1"), true);
        submitContactCreation();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactsCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

//    public List<ContactData> getContactsList() {
//        List<ContactData> contacts = new ArrayList<ContactData>();
//        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
//        for (WebElement element : elements){
//            String lastName = element.findElement(By.xpath("//td[2]")).getText();
//            String firstName = element.findElement(By.xpath("//td[3]")).getText();
//            String address = element.findElement(By.xpath("//td[4]")).getText();
//            String email = element.findElement(By.xpath("//td[5]")).getText();
//            String phones = element.findElement(By.xpath("//td[6]")).getText();
//            List<String> phonesList = new ArrayList<String>();
//            for (String phone : phones.split("\n")) {
//                phonesList.add(phone);
//            }
//            String homePhone = phonesList.get(0);
//            String mobile = phonesList.get(1);
//            ContactData contact = new ContactData(firstName, lastName, null, address, homePhone, mobile, email, null);
//            ContactData contact = new ContactData(firstName, lastName, null, null, null, null, null, null);
//            contacts.add(contact);
//        }
//
//        return contacts;
//    }


    public List<ContactData> getContactsList() {
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
            switch (phonesList.size()){
                case 1:
                    homePhone = phonesList.get(0);
                    break;
                case 2:
                    homePhone = phonesList.get(0);
                    mobile = phonesList.get(1);
                    break;
            }
            phonesList.clear();

            ContactData contact = new ContactData(firstName, lastName, null, address, homePhone, mobile, email, null);
            contacts.add(contact);
        }
        return contacts;
    }


    public String getPhone() {
        return wd.findElement(By.xpath("//tr[@name='entry']/td[6]")).getText();
    }
}
