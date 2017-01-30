package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.BaseTest;

/**
 * Created by Michael on 20.01.2017.
 */
public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnGroupPage() {
        click(By.xpath("//a[contains(text(),'group page')]"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupData(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void groupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void modifySelectedGroup() {
        click(By.name("edit"));
    }

    public void updateNewGroupData() {
        click(By.name("update"));
    }

    public void createGroup(GroupData group) {
        groupCreation();
        fillGroupData(group);
        submitGroupCreation();
        returnGroupPage();


    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }
}
