package com.crossover.utilmethods;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.crossover.pageobjects.PageObjects;

public class CommonMethods {

    PageObjects pageObjects = new PageObjects();
    ActionsOnPage actionsOnPage = new ActionsOnPage();

    public void openURL(WebDriver driver, String url) {
        actionsOnPage.openURL(driver, url);
        driver.manage().window().maximize();
    }

    public void enterEmailAddress(WebDriver driver, String email) {
        actionsOnPage.sendKeys(driver, By.id(pageObjects.UserName_TextBox), email);
    }

    public void clickNextAfterEnteringEmail(WebDriver driver) {
        actionsOnPage.click(driver, By.id(pageObjects.UserName_Next));
    }

    public void enterPassword(WebDriver driver, String password) {
        actionsOnPage.sendKeys(driver, By.name(pageObjects.Password_TextBox), password);
    }

    public void clickNextAfterEnteringPassword(WebDriver driver) {
        actionsOnPage.click(driver, By.id(pageObjects.Password_Next));
    }

    public void composeEmailAndSend(WebDriver driver, String to, String subject, String body) {
        actionsOnPage.click(driver, By.xpath(pageObjects.Compose_Button));
        actionsOnPage.sendKeys(driver, By.name(pageObjects.Mail_TO), String.format("%s@gmail.com", to));
        actionsOnPage.sendKeys(driver, By.name(pageObjects.Mail_TO), Keys.ENTER);
        actionsOnPage.sendKeys(driver, By.name(pageObjects.Mail_Subject), subject);
        actionsOnPage.buildActions(driver, By.xpath(pageObjects.Mail_Body), body);
        actionsOnPage.click(driver, By.xpath(pageObjects.More_Options));
        actionsOnPage.click(driver, By.xpath(pageObjects.Label_Selector));
        actionsOnPage.sendKeys(driver, By.xpath(pageObjects.Enter_Label), "Social");
        actionsOnPage.sendKeys(driver, By.xpath(pageObjects.Enter_Label), Keys.ENTER);
        actionsOnPage.click(driver, By.xpath(pageObjects.Mail_Send));
    }

    public void MarkEmailAsStarred(WebDriver driver) {
        actionsOnPage.click(driver, By.xpath(pageObjects.Click_Star));
    }

    public void clickEmail(WebDriver driver) {
        actionsOnPage.click(driver, By.xpath(pageObjects.Click_Social));
        actionsOnPage.click(driver, By.xpath(pageObjects.Mail_Refresh));
        actionsOnPage.checkReceivedEmailInSocialTag(driver);
    }


    public boolean verifyEmailSubject(WebDriver driver, String subject) {
        if (actionsOnPage.checkElementIsDisplayed(driver,
                By.xpath(pageObjects.Check_Mail_Subject.replaceAll("Subject of this message", subject))))
            return true;
        else
            return false;
    }

    public boolean verifyEmailBody(WebDriver driver, String body) {
        if (actionsOnPage.checkElementContainsText(driver, By.xpath(pageObjects.Mail_Body_Received), body))
            return true;
        else
            return false;
    }


}
