package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 09.10.2016.
 */
public class ApplicationManager {

    WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private ContactHelper contactHelper;
    private GroupHelper groupHelper;



    public void init(String browser) {

        if(browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        }
        else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        }
        else if(browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }
        sessionHelper = new SessionHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        sessionHelper.login();

    }


    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }
}
