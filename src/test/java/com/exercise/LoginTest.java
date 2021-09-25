package com.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {

    @Test
    void successfulRedirectToLoginPage() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // launch application
        driver.get("https://www.comm100.com/");

        // click sign in button
        WebElement signIn = driver.findElement(By.xpath("//a[.='Sign In']"));
        signIn.click();

        // verify login page launched successfully

        // verify user login form is present
        WebElement userForm = driver.findElement(By.id("divLogin"));
        assertTrue(userForm.isDisplayed(), "User form is not displayed on Login Screen");

        driver.close();

    }

    @Test
    void invalidLogin() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // launch application
        driver.get("https://www.comm100.com/");

        // click sign in button
        WebElement signIn = driver.findElement(By.xpath("//a[.='Sign In']"));
        signIn.click();

        // enter invalid username
        WebElement username = driver.findElement(By.name("loginemail"));
        username.sendKeys("abacaa@abc.com");

        // enter invalid password
        WebElement password = driver.findElement(By.name("loginpassword"));
        password.sendKeys("invalid");

        // click sign in button
        WebElement signInButton = driver.findElement(By.id("lblLogin"));
        signInButton.click();

        // wait for error container to be displayed
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dialogerrorcontainer']")));

        WebElement errorContainer = driver.findElement(By.xpath("//div[@class='dialogerrorcontainer']"));
        assertTrue(errorContainer.isDisplayed(), "Error Container not present");

        // verify error message
        WebElement errorTitle = driver.findElement(By.xpath("//div[@class='dialogtitle']"));
        String errorMessage = errorTitle.getText().trim();
        assertEquals("Error signing into the account", errorMessage);

        driver.close();

    }

}
