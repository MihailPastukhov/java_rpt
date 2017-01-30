package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;

/**
 * Created by Michael on 23.01.2017.
 */
public class BaseHelper {
    protected WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);
        if (!(text == null)){
            String currentText = wd.findElement(locator).getAttribute("value");
            if(!text.equals(currentText)){
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    public boolean isElementPresent(By locator){
        try {
            wd.findElement(locator);
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
