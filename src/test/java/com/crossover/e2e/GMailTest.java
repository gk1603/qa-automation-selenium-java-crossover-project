package com.crossover.e2e;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import junit.framework.TestCase;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import com.crossover.utilmethods.*;


public class GMailTest extends TestCase {
    private WebDriver driver;
    private Properties properties = new Properties();
    private String URL = "https://mail.google.com/";
    ExtentHtmlReporter htmlreport = new ExtentHtmlReporter("./reports/GmailTestCases.html");
    static ExtentTest testlog;
    static ExtentReports report;

    CommonMethods commonMethods = new CommonMethods();

    public void setUp() throws Exception {
        report = new ExtentReports();
        report.attachReporter(htmlreport);
        properties.load(new FileReader(new File("src/test/resources/test.properties")));
        //Dont Change below line. Set this value in test.properties file incase you need to change it..
        System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
        driver = new ChromeDriver();
        testlog = report.createTest("Gmail Test");
    }

    public void tearDown() throws Exception {
        driver.quit();
        report.flush();
    }

    /*
     * Please focus on completing the task
     *
     */
    @Test
    public void testSendEmail() throws Exception {

        /*driver.get("https://mail.google.com/");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement userElement = driver.findElement(By.id("identifierId"));
        userElement.sendKeys(properties.getProperty("username"));
        driver.findElement(By.id("identifierNext")).click();

        Thread.sleep(1000);

        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(properties.getProperty("password"));
        driver.findElement(By.id("passwordNext")).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);

        //Instead of using an explicit sleep , we can use conditional wait for compose element to be visible
        // Thread.sleep(1000);

        //1.Bug Fix : xpath text is incorrect.
        // WebElement composeElement = driver.findElement(By.xpath("//*[@role='button' and (.)='COMPSE']"));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Compose']"))).click();
        driver.findElement(By.name("to")).clear();
        driver.findElement(By.name("to")).sendKeys(String.format("%s@gmail.com", properties.getProperty("username")));

        // emailSubject and emailbody to be used in this unit test.
        String emailSubject = properties.getProperty("email.subject");
        String emailBody = properties.getProperty("email.body");

        //2.Bug fix : code for email subject and body being entered in the email is not written

        //3.Bug Fix : send button needs to be clicked after setting email subject and body.
        driver.findElement(By.xpath("//*[@role='button' and text()='Send']")).click();*/


 /*
  -------------------------------------
  */

        /*
        * Writing a new piece of code altogether to separate the existing and the new code.
          For reporting, extentReports have been used.
        */

        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String emailSubject = properties.getProperty("email.subject");
        String emailBody = properties.getProperty("email.body");

        commonMethods.openURL(driver, URL);
        testlog.log(Status.PASS, "Navigated to the specified URL");
        commonMethods.enterEmailAddress(driver, username);
        testlog.log(Status.PASS, "username entered correctly");
        commonMethods.clickNextAfterEnteringEmail(driver);
        commonMethods.enterPassword(driver, password);
        testlog.log(Status.PASS, "password entered correctly");
        commonMethods.clickNextAfterEnteringPassword(driver);
        commonMethods.composeEmailAndSend(driver, username, emailSubject, emailBody);
        testlog.log(Status.PASS, "Email Composed and sent successfully");
        commonMethods.clickEmail(driver);
        testlog.log(Status.PASS, "Email received and clicked successfully");
        commonMethods.MarkEmailAsStarred(driver);
        testlog.log(Status.PASS, "Email marked starred successfully");
        assertTrue("Verifying Email Subject", commonMethods.verifyEmailSubject(driver, emailSubject));
        testlog.log(Status.PASS, "Email subject verified successfully");
        assertTrue("Verifying Email Content", commonMethods.verifyEmailBody(driver, emailBody));
        testlog.log(Status.PASS, "Email Body verified successfully");
    }
}
