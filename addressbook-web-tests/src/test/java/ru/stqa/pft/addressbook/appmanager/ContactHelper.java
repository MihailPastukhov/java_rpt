package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

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

    public void selectContact() {
        click(By.xpath("//input[@name='selected[]']"));
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
}
