package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Michael on 23.01.2017.
 */
public class SessionHelper extends BaseHelper {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login() {
        type(By.name("user"), "admin");
        type(By.name("pass"), "secret");
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }

}
