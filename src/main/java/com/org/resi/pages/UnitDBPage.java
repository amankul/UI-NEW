package com.org.resi.pages;

import com.org.resi.constants.LogInConstants;
import com.org.resi.constants.UnitDBConstants;
import com.org.resi.utilities.PropertiesLoader;
import com.org.resi.utilities.SeleniumUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

public class UnitDBPage {

    public WebDriver driver;
    public String data;
    public String propertyFile;
    public SeleniumUtil seleniumUtil = null;
    private static Logger log = LogManager.getLogger();

    public UnitDBPage(WebDriver driver, String data) {
        this.driver = driver;
        this.data = data;
        propertyFile =
                System.getProperty("user.dir") + "/src/main/java/com/org/resi/properties/" + data;
        seleniumUtil = new SeleniumUtil();
    }

    public void show(String type) {
        seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_SHOW_DROPDOWN));
        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver, By.xpath(UnitDBConstants.LOCATOR_SHOW_TYPE.replace("replaceText", type)))
                .click();
    }

    public void clickAddNew() {
        seleniumUtil.hover(driver, By.xpath(UnitDBConstants.LOCATOR_ACTIONS_DROPDOWN));
        seleniumUtil.sleep(2);
        seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_ACTIONS_DROPDOWN));
        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_ACTIONS_ADDNEW))
                .click();
        seleniumUtil.waitForPageToLoad(driver);
        log.info("Click action on Add new");
    }

    public void selectUnitNumber(String unit) {

            seleniumUtil
                    .wait_until_ElementIs_Clickable(
                            driver, By.xpath(UnitDBConstants.LOCATOR_UNIT_NUMBER_TEXTBOX))
                    .clear();

            seleniumUtil
                    .wait_until_ElementIs_Clickable(
                            driver, By.xpath(UnitDBConstants.LOCATOR_UNIT_NUMBER_TEXTBOX))
                    .sendKeys(unit);

            seleniumUtil.wait_until_ElementIs_Clickable(driver,By.xpath(UnitDBConstants.LOCATOR_UNIT_AUTO_SUGGEST));
            seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_UNIT_AUTO_SUGGEST));
            log.info("Unit Selected -" + unit);

    }

    public void sendFirstAndLastName(String firstName, String lastName) {

        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver, By.xpath(UnitDBConstants.LOCATOR_FIRST_NAME_TEXTBOX))
                .sendKeys(firstName);

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_LAST_NAME_TEXTBOX))
                .sendKeys(lastName);

        log.info("First/Last Name Sent");
    }

    public void selectLanguage(String language) {
        seleniumUtil.hoverClickSendDataAndPressEnter(
                driver, By.xpath(UnitDBConstants.LOCATOR_LANGUAGE_DROPDOWN), language);
        log.info("Language Selected");
    }

    public void selectMoveIn_MoveOutDates(String moveIn, String moveOut) {

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_MOVEIN))
                .click();

        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver, By.xpath(UnitDBConstants.LOCATOR_DATE_SELECTION.replace("ReplaceText", moveIn)))
                .click();

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_MOVEOUT))
                .click();

        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver,
                        By.xpath(UnitDBConstants.LOCATOR_DATE_SELECTION.replace("ReplaceText", moveOut)))
                .click();
        log.info("Selected MoveIn/MoveOut dates");
    }

    public void selectMoveIn_MoveOutDates() {

        String moveIn = LocalDate.now().toString().replaceAll("-", "");
        String moveOut = LocalDate.now().plusDays(2).toString().replaceAll("-", "");

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_MOVEIN))
                .click();

        seleniumUtil.hoverAndClick(
                driver, By.xpath(UnitDBConstants.LOCATOR_DATE_SELECTION.replace("ReplaceText", moveIn)));

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_MOVEOUT))
                .click();

        seleniumUtil.hoverAndClick(
                driver, By.xpath(UnitDBConstants.LOCATOR_DATE_SELECTION.replace("ReplaceText", moveOut)));

        log.info("Selected MoveIn/MoveOut dates");
    }

    public void addRandomPhoneNumber(String phoneNumber) {

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_GENERAL_TAB))
                .click();

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_PHONE_TAB))
                .click();

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_ADD_PHONE_BUTTON))
                .click();

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_PHONE_TEXT_BOX))
                .clear();

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_PHONE_TEXT_BOX))
                .sendKeys(phoneNumber);

        log.info("Phone Number sent -" + phoneNumber);
    }


    public void deleteResidentPhoneNumber() {

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_GENERAL_TAB))
                .click();

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_PHONE_TAB))
                .click();

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_RESIDENT_DELETE_PHONE))
                .click();

        log.info("Deleted Phone Number");
    }


    public boolean IsAccessErrorMessageAvailable() {
        return driver.findElements(By.xpath(UnitDBConstants.LOCATOR_ACCESS_ERROR_MESSAGE)).size() >= 1;
    }

    public void addAccess(String access) {

        clickAccessTab();

        // click on `+` only if there is no access point select option
        log.info("Checking for Add Access Entry");
        if (driver.findElements(By.xpath(UnitDBConstants.LOCATOR_SELECT_ACCESS_POINT)).size() == 0) {
            seleniumUtil.sleep(1);
            seleniumUtil
                    .wait_until_ElementIs_Clickable(
                            driver, By.xpath(UnitDBConstants.LOCATOR_ADD_ACCESS + "[last()]"))
                    .click();
        }

        seleniumUtil.hoverClickSendDataAndPressEnter(
                driver, By.xpath(UnitDBConstants.LOCATOR_SELECT_ACCESS_POINT + "[last()]"), access);

        log.info("Access - " + access + " Included");
        seleniumUtil.waitForPageToLoad(driver);
    }

    public void remove_UAL_Outputs_Only() {

        clickAccessTab();
        seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_REMOVE_UAL_OUTPUTS_ONLY));
        seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_OK_BUTTON));

        log.info("Removed UAl With Outputs");
    }

    public void remove_All_Access(int accessNumber) {

        clickAccessTab();

        log.info("Trying to remove -" + accessNumber + " access permissions");
        while (accessNumber >= 1) {
            log.info("Access Remaining -" + accessNumber);
            seleniumUtil.jsFocusAndClick(
                    driver, By.xpath(UnitDBConstants.LOCATOR_REMOVE_ACCESS + "[last()]"));

            int retry = 0;

            while (driver.findElements(By.xpath(UnitDBConstants.LOCATOR_OK_BUTTON)).size() == 0
                    && retry < 10
                    && accessNumber >= 1) {
                seleniumUtil.jsFocusAndClick(
                        driver, By.xpath(UnitDBConstants.LOCATOR_REMOVE_ACCESS + "[last()]"));
                seleniumUtil.sleep(1);
                retry++;
            }

            seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_OK_BUTTON));
            accessNumber--;
        }

        log.info("All Access removed");
        seleniumUtil.waitForPageToLoad(driver);
        // seleniumUtil.sleep(2);
    }

    public boolean isAllAccessRemoved() {

        clickAccessTab();

        if (driver.findElements(By.xpath(UnitDBConstants.LOCATOR_REMOVE_ACCESS)).size() == 1) {
            log.info("found only one remove access Button");
            if (driver.findElements(By.xpath(UnitDBConstants.LOCATOR_SELECT_ACCESS_POINT)).size() == 1) {
                log.info("No Access Found in Access Tab");
                return true;
            }
        }
        return false;
    }

    public void restrictAccess(String accessName) {

        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver, By.xpath(UnitDBConstants.LOCATOR_RESTRICT_ACCESS_CHECKBOX + "[last()]"))
                .click();
        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver, By.xpath(UnitDBConstants.LOCATOR_SELECT_DATES_CHECKBOX + "[last()]"))
                .click();

        seleniumUtil.hoverClickSendDataAndPressEnter(
                driver, By.xpath(UnitDBConstants.LOCATOR_SELECT_TIME_TABLE + "[last()]"), "QA");
        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver, By.xpath(UnitDBConstants.LOCATOR_SELECT_TIME_TABLE + "[last()]"))
                .click();

        log.info("Restricted access");
    }

    public void clickSave() {
        seleniumUtil.jsFocusAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_SAVE_BUTTON));
        log.info("Click action on save");
        seleniumUtil.sleep(2);
    }

    public void clickCancel() {
        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_CANCEL_BUTTON))
                .click();
        log.info("Click action on cancel");
    }

    public void clickResident(String name) {

        search(name);

        log.info("Trying to click on Resident");
        seleniumUtil.hoverAndClick(
                driver, By.xpath(UnitDBConstants.LOCATOR_RESIDENT.replace("replaceText", name)));

        int retry = 0;

        while (driver.findElements(By.xpath(UnitDBConstants.LOCATOR_EDIT_OCCUPANT_TEXT)).size() == 0
                && retry < 10) {
            seleniumUtil.hoverAndClick(
                    driver, By.xpath(UnitDBConstants.LOCATOR_RESIDENT.replace("replaceText", name)));
            retry++;
        }

        log.info("Click action on resident complete");
    }


    public boolean isResidentAvailable(String name) {

        search(name);
        log.info("Trying to Search on Resident");
        return driver.findElements(By.xpath(UnitDBConstants.LOCATOR_RESIDENT.replace("replaceText", name))).size() >= 1;

    }

    public boolean isAccessErrorMessageAvailable() {

        log.info("Trying to Search for access error message with xpath -" + UnitDBConstants.LOCATOR_ACCESS_ERROR_MESSAGE);

        if (driver.findElements(By.xpath(UnitDBConstants.LOCATOR_ACCESS_ERROR_MESSAGE)).size() >= 1) {
            return true;
        }

        log.info("Trying to Search for access error message with xpath -" + UnitDBConstants.LOCATOR_ACCESS_ERROR_MESSAGE_2);

        if (driver.findElements(By.xpath(UnitDBConstants.LOCATOR_ACCESS_ERROR_MESSAGE_2)).size() >= 1) {
            return true;
        }

        return false;
    }


    public boolean isAccessPresent_UAL_OutputsOnly() {
        clickAccessTab();
        return driver.findElements(By.xpath(UnitDBConstants.LOCATOR_UAL_OUTPUTS_ONLY)).size() == 1;
    }

    public boolean isAccessPresent_UAL_DoorsZones_And_Outputs() {
        clickAccessTab();
        return driver.findElements(By.xpath(UnitDBConstants.LOCATOR_UAL_DOORS_ZONES_OUTPUTS)).size()
                == 1;
    }

    public boolean isAccessPresent_UAL_Doors_ZonesOnly() {
        clickAccessTab();
        return driver.findElements(By.xpath(UnitDBConstants.LOCATOR_UAL_DOORS_ZONES_ONLY)).size() == 1;
    }

    public boolean isAccessPresent_UAL_SD_Online_Doors_only() {
        clickAccessTab();
        return driver.findElements(By.xpath(UnitDBConstants.LOCATOR_SD_ONLINE_DOORS_ONLY)).size() == 1;
    }

    public boolean isAccessPresent_FalseDoor_only() {
        clickAccessTab();
        return driver.findElements(By.xpath(UnitDBConstants.LOCATOR_FALSE_DOOR)).size() == 1;
    }

    public boolean deleteResidentAndVerify(String name) {

        seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_DELETE_BUTTON));
        seleniumUtil.sleep(2);
        seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_OK_BUTTON));
        seleniumUtil.sleep(2);
        log.info("Delete Action on Resident -" + name + " performed");
        log.info(
                "Validating element not present with x-path -"
                        + UnitDBConstants.LOCATOR_RESIDENT.replace("replaceText", name));
        return driver
                .findElements(By.xpath(UnitDBConstants.LOCATOR_RESIDENT.replace("replaceText", name)))
                .size()
                == 0;
    }

    public void search(String name) {
        seleniumUtil.hoverAndClear(driver, By.xpath(UnitDBConstants.LOCATOR_SEARCH_BOX));
        seleniumUtil.sleep(1);
        seleniumUtil.hoverAndSendData(driver, By.xpath(UnitDBConstants.LOCATOR_SEARCH_BOX), name);
        seleniumUtil.sleep(2);
    }

    public void clickAccessTab() {

        seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_ACCESS_TAB));
        seleniumUtil.sleep(1);
        seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_ACCESS_TAB));
        seleniumUtil.sleep(1);
        seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_ACCESS_TAB));
    }

    //   **************** VISITOR FUNCTIONS ***************** //

    public void clickAddNewVisitor() {
        seleniumUtil.hover(driver, By.xpath(UnitDBConstants.LOCATOR_ACTIONS_DROPDOWN));
        seleniumUtil.sleep(1);
        seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_ACTIONS_DROPDOWN));
        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_ACTIONS_ADDNEW))
                .click();
        seleniumUtil.waitForPageToLoad(driver);
        log.info("Click action on Add new");
        seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_ACTIONS_ADDNEW_VISITOR));
        seleniumUtil.waitForPageToLoad(driver);
        log.info("Click action on Add new Visitor");
    }

    public void selectVisitorUnitNumber(String unit) {

        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver, By.xpath(UnitDBConstants.LOCATOR_UNIT_NUMBER_TEXTBOX_VISITOR))
                .sendKeys(unit);
        seleniumUtil.sleep(1);
        seleniumUtil.waitForPageToLoad(driver);
        seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_UNIT_AUTO_SUGGEST));
        log.info("Unit Selected -" + unit);
    }

    public void sendFirstAndLastNameVisitor(String firstName, String lastName) {

        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver, By.xpath(UnitDBConstants.LOCATOR_VISITOR_FIRST_NAME_TEXTBOX))
                .sendKeys(firstName);

        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver, By.xpath(UnitDBConstants.LOCATOR_VISITOR_LAST_NAME_TEXTBOX))
                .sendKeys(lastName);

        log.info("First/Last Name Sent");
    }

    public void addRandomPhoneNumberVisitor(String phoneNumber) {

        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_GENERAL_TAB))
                .click();

        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver, By.xpath(UnitDBConstants.LOCATOR_VISITOR_PHONE_TEXTBOX))
                .sendKeys(phoneNumber);

        log.info("Phone Number sent -" + phoneNumber);
    }


    public void removePhoneNumberVisitor() {
        seleniumUtil
                .wait_until_ElementIs_Clickable(driver, By.xpath(UnitDBConstants.LOCATOR_GENERAL_TAB))
                .click();

        seleniumUtil
                .wait_until_ElementIs_Clickable(
                        driver, By.xpath(UnitDBConstants.LOCATOR_VISITOR_PHONE_TEXTBOX))
                .clear();

        log.info("Visitors Phone Number Removed");
    }

    public void clickVisitor(String name) {

        search(name);

        log.info("Trying to click on Visitor");
        seleniumUtil.hoverAndClick(
                driver, By.xpath(UnitDBConstants.LOCATOR_RESIDENT.replace("replaceText", name)));

        int retry = 0;

        while (driver.findElements(By.xpath(UnitDBConstants.LOCATOR_EDIT_OCCUPANT_TEXT)).size() == 0
                && retry < 10) {
            seleniumUtil.hoverAndClick(
                    driver, By.xpath(UnitDBConstants.LOCATOR_RESIDENT.replace("replaceText", name)));
            // seleniumUtil.sleep(2);
            retry++;
        }

        log.info("Click action on visitor complete");
    }


    public boolean isVisitorAvailable(String name) {
        search(name);
        log.info("Trying to Search on Visitor -" + name);
        return driver.findElements(By.xpath(UnitDBConstants.LOCATOR_RESIDENT.replace("replaceText", name))).size() >= 1;
    }

    public boolean deleteVisitorAndVerify(String name, String LastName) {

        search(LastName);

        seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_DELETE_BUTTON));
        seleniumUtil.sleep(2);
        seleniumUtil.hoverAndClick(driver, By.xpath(UnitDBConstants.LOCATOR_OK_BUTTON));
        seleniumUtil.sleep(1);
        log.info("Delete Action on Visitor -" + name + " performed");
        log.info(
                "Validating element not present with x-path -"
                        + UnitDBConstants.LOCATOR_RESIDENT.replace("replaceText", name));
        return driver
                .findElements(By.xpath(UnitDBConstants.LOCATOR_RESIDENT.replace("replaceText", name)))
                .size()
                == 0;
    }


}
