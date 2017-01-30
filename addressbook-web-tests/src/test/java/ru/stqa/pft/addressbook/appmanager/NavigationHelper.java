package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Michael on 20.01.2017.
 */
public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToHomePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.xpath("//a[contains(text(),'home')]"));
    }

    public void goToGroupPage() {
        if (isElementPresent(By.tagName("h1"))
                && (wd.findElement(By.tagName("h1")).getText().equals("groups"))
                && (isElementPresent(By.name("new")))){
            return;
        }
        click(By.xpath(".//a[contains(text(),'groups')]"));
    }
}
