package com.org.resi.pages;

import com.org.resi.constants.LogInConstants;
import com.org.resi.utilities.PropertiesLoader;
import com.org.resi.utilities.SeleniumUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {

    public WebDriver driver;
    public String data;
    public String propertyFile;
    public SeleniumUtil seleniumUtil = null;
    private static Logger log = LogManager.getLogger();


    public LoginPage(WebDriver driver, String data) {
        this.driver = driver;
        this.data = data;
        propertyFile =
                System.getProperty("user.dir") + "/src/main/java/com/org/resi/properties/" + data;
        seleniumUtil = new SeleniumUtil();
    }

    public void logIn() {
        log.info("PAGE TITLE BEFORE USER LOGIN - " + driver.getTitle());

        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver, By.cssSelector(LogInConstants.LOCATOR_EMAIL_TEXT_FIELD))
                .sendKeys(PropertiesLoader.readDataFromPropertiesFile("username", propertyFile));

        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver, By.cssSelector(LogInConstants.LOCATOR_PASSWORD_TEXT_FIELD))
                .sendKeys(PropertiesLoader.readDataFromPropertiesFile("password", propertyFile));

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(LogInConstants.LOCATOR_LOGIN_BUTTON_XPATH))
                .click();

        seleniumUtil.waitForPageToLoad(driver);
    }

    public void selectCompany() {

        String parentHandle = driver.getWindowHandle();
        log.info("Parent Handle -" + parentHandle);

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.id("cboCompany"))
                .sendKeys(PropertiesLoader.readDataFromPropertiesFile("company", propertyFile));

        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver, By.xpath(LogInConstants.LOCATOR_SELECT_BUTTON_XPATH))
                .click();

        seleniumUtil.wait_until_Two_Windows_Are_Available(driver);

        seleniumUtil.switchToNewWindowHandle(driver, parentHandle);
        seleniumUtil.waitForPageToLoad(driver);
    }

    public void navigateToUnitDB() {
        seleniumUtil.hoverAndClick(driver, By.xpath(LogInConstants.LOCATOR_MODULES_XPATH));
        seleniumUtil.hoverAndClick(driver, By.xpath(LogInConstants.LOCATOR_UNIT_DB_XPATH));
        seleniumUtil.waitForPageToLoad(driver);
    }
}
