package com.org.resi;

import com.org.resi.pages.LoginPage;
import com.org.resi.pages.UnitDBPage;
import com.org.resi.utilities.SeleniumUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class ResidentVisitorWithoutPhoneNumber {

    public static Logger log = LogManager.getLogger();
    private WebDriver driver = null;
    private LoginPage lp;
    private UnitDBPage uDB;
    private SeleniumUtil seleniumUtil = null;
    private String firstName = "Automation";
    private String lastName = LocalDate.now().toString().replaceAll("-", "");
    String phoneNumber = String.valueOf(Math.random() * 10).replace(".", "").substring(0, 10);
    String phoneNumberVisitor = String.valueOf(Math.random() * 10).replace(".", "").substring(0, 10);

    @BeforeMethod
    @Parameters({"url", "browser", "browserVersion", "targetRun", "data"})
    public void login(
            String url, String browser, String browserVersion, String targetRun, String data) {

        seleniumUtil = new SeleniumUtil();

        // Creating driver object based on browser stack configuration
        if (targetRun.equalsIgnoreCase("browserStack")) {
            driver = seleniumUtil.createBrowserStackDriver(url, browser, browserVersion);
        } else if (targetRun.equalsIgnoreCase("local")) {
            driver = seleniumUtil.createDriver(url, browser);
        } else {
            log.info("Invalid `runOn` value present in testNG_TechciergeStage.xml");
            throw new SkipException("Skipping tests");
        }

        log.info("Selenium Web Driver session initiated on -" + targetRun);

        lp = new LoginPage(driver, data);
        uDB = new UnitDBPage(driver, data);
        lp.logIn();
        lp.selectCompany();
        lp.navigateToUnitDB();
    }

    /* CREATE RESIDENT OR VISITOR  WITHOUT REQUIRING A PHONE NUMBER */


    /*
    Create Resident without Phone Number
     */
    @Test(priority = 1)
    public void verify_AddResidentWithoutPhoneNumber() {

        // Create Resident With UAL's
        uDB.show("Occupants");
        uDB.clickAddNew();
        uDB.selectUnitNumber("Automat");
        uDB.sendFirstAndLastName(firstName, lastName);
        uDB.selectLanguage("English");
        uDB.selectMoveIn_MoveOutDates();
        uDB.clickSave();

        Assert.assertTrue(
                uDB.isResidentAvailable(lastName + ", " + firstName),
                "Unable to create a resident without phone number");
        uDB.clickResident(lastName + ", " + firstName);
        uDB.deleteResidentAndVerify(lastName + ", " + firstName);
    }

    /*
  Try Creating a Resident without Phone Number, with access
   */
    @Test(priority = 2)
    public void verify_AddResidentWithoutPhoneNumberWithAccess() {

        // Create Resident With UAL's
        uDB.show("Occupants");
        uDB.clickAddNew();
        uDB.selectUnitNumber("Automat");
        uDB.sendFirstAndLastName(firstName, lastName);
        uDB.selectLanguage("English");
        uDB.selectMoveIn_MoveOutDates();
        uDB.addAccess("UAL - Outputs Only");
        uDB.clickSave();

        Assert.assertTrue(
                uDB.isAccessErrorMessageAvailable(),
                "Unable to find access error message.");

    }

    /*
  Try Creating a Resident without Phone Number & with access initially, verify error message, add phone number and
  */
    @Test(priority = 3)
    public void verify_AddResidentWithPhoneNumberWithAccess() {

        // Create Resident With UAL's
        uDB.show("Occupants");
        uDB.clickAddNew();
        uDB.selectUnitNumber("Automat");
        uDB.sendFirstAndLastName(firstName, lastName);
        uDB.selectLanguage("English");
        uDB.selectMoveIn_MoveOutDates();
        uDB.addAccess("UAL - Outputs Only");
        uDB.clickSave();

        Assert.assertTrue(
                uDB.isAccessErrorMessageAvailable(),
                "Unable to find access error message.");

        uDB.addRandomPhoneNumber(phoneNumber);
        uDB.clickSave();

        Assert.assertTrue(
                uDB.isResidentAvailable(lastName + ", " + firstName),
                "Unable to create a resident without phone number");

        uDB.clickResident(lastName + ", " + firstName);
        uDB.deleteResidentAndVerify(lastName + ", " + firstName);
    }


    /*
    Create Visitor without Phone Number
     */
    @Test(priority = 4)
    public void verify_AddVisitorWithUAL() {

        uDB.show("Visitors");
        uDB.clickAddNewVisitor();
        uDB.selectVisitorUnitNumber("Automat");
        uDB.sendFirstAndLastNameVisitor(firstName, lastName);
        uDB.clickSave();
        Assert.assertTrue(uDB.isVisitorAvailable(lastName), "Unable to create a visitor without phone number");
        uDB.clickVisitor(lastName);
        uDB.deleteVisitorAndVerify(lastName + " " + firstName, lastName);

    }


    /*
  Try Creating Visitor without Phone Number, with access
   */
    @Test(priority = 5)
    public void verify_WithoutPhoneNumberWithAccess() {

        uDB.show("Visitors");
        uDB.clickAddNewVisitor();
        uDB.selectVisitorUnitNumber("Automat");
        uDB.sendFirstAndLastNameVisitor(firstName, lastName);
        uDB.addAccess("UAL - Outputs Only");
        uDB.clickSave();

        Assert.assertTrue(
                uDB.isAccessErrorMessageAvailable(),
                "Unable to find access error message.");
    }


    /*
  Try Creating a Visitor without Phone Number & with access initially, verify error message, add phone number and
  */
    @Test(priority = 6)
    public void verify_AddVisitorWithPhoneNumberWithAccess() {

        uDB.show("Visitors");
        uDB.clickAddNewVisitor();
        uDB.selectVisitorUnitNumber("Automat");
        uDB.sendFirstAndLastNameVisitor(firstName, lastName);
        uDB.addAccess("UAL - Outputs Only");
        uDB.clickSave();

        Assert.assertTrue(
                uDB.isAccessErrorMessageAvailable(),
                "Unable to find access error message.");

        uDB.addRandomPhoneNumberVisitor(phoneNumberVisitor);
        uDB.clickSave();
        Assert.assertTrue(uDB.isVisitorAvailable(lastName), "Unable to create a visitor without phone number");
        uDB.clickVisitor(lastName);
        uDB.deleteVisitorAndVerify(lastName + " " + firstName, lastName);

    }


    /* UPDATE RESIDENT OR VISITOR  WITHOUT REQUIRING A PHONE NUMBER */


    /*
Create Resident with Phone Number and access, Edit - remove phone number and try to save, Check for error message
 */
    @Test(priority = 7)
    public void verify_UpdateResident_WithAccess_RemovePhone() {

        // Create Resident With Phone Number and access.
        uDB.show("Occupants");
        uDB.clickAddNew();
        uDB.selectUnitNumber("Automat");
        uDB.sendFirstAndLastName(firstName, lastName);
        uDB.selectLanguage("English");
        uDB.selectMoveIn_MoveOutDates();
        uDB.addAccess("UAL - Outputs Only");
        uDB.addRandomPhoneNumber(phoneNumber);
        uDB.clickSave();

        Assert.assertTrue(
                uDB.isResidentAvailable(lastName + ", " + firstName),
                "Unable to create a resident without phone number");


        //Edit - remove phone number
        uDB.clickResident(lastName + ", " + firstName);
        uDB.deleteResidentPhoneNumber();

        Assert.assertTrue(
                uDB.isAccessErrorMessageAvailable(),
                "Unable to find access error message.");

    }


    /*
Edit Resident with Phone Number and access, Edit - remove phone number  and Access, User should be able to save.
*/
    @Test(priority = 8)
    public void verify_UpdateResident_RemoveAccessAndPhoneNumber() {

        // Create Resident With Phone Number and access.
        uDB.clickResident(lastName + ", " + firstName);
        uDB.remove_UAL_Outputs_Only();
        uDB.deleteResidentPhoneNumber();
        uDB.clickSave();

        Assert.assertFalse(
                uDB.isAccessErrorMessageAvailable(),
                "Access error message is observed");

    }


    /*
Edit Resident who does not have Phone Number and access, Add Access only, User should not be able to save.
*/
    @Test(priority = 9)
    public void verify_UpdateResident_AddAccessOnly() {

        // Create Resident With Phone Number and access.
        uDB.clickResident(lastName + ", " + firstName);
        uDB.addAccess("UAL - Outputs Only");
        uDB.clickSave();

        Assert.assertTrue(
                uDB.isAccessErrorMessageAvailable(),
                "Unable to find access error message.");

    }


    /*
Edit Resident who does not have Phone Number and access, Add Access and Phone Number, User should be able to save.
*/
    @Test(priority = 10)
    public void verify_UpdateResident_AddAccessAndPhone() {

        // Create Resident With Phone Number and access.
        uDB.clickResident(lastName + ", " + firstName);
        uDB.addAccess("UAL - Outputs Only");
        uDB.addRandomPhoneNumber(phoneNumber);
        uDB.clickSave();

        Assert.assertFalse(
                uDB.isAccessErrorMessageAvailable(),
                "Access error message is observed");

        //Test Data clean up
        uDB.clickResident(lastName + ", " + firstName);
        uDB.deleteResidentAndVerify(lastName + ", " + firstName);
    }


    /*
Create Visitor with Phone Number and access, Edit - remove phone number and try to save, Check for error message
*/
    @Test(priority = 11)
    public void verify_UpdateVisitor_WithAccess_RemovePhone() {

        // Create Visitor With Phone Number and access.
        uDB.show("Visitors");
        uDB.clickAddNewVisitor();
        uDB.selectVisitorUnitNumber("Automat");
        uDB.sendFirstAndLastNameVisitor(firstName, lastName);
        uDB.addAccess("UAL - Outputs Only");
        uDB.clickSave();
        uDB.addRandomPhoneNumberVisitor(phoneNumberVisitor);
        uDB.clickSave();

        Assert.assertTrue(uDB.isVisitorAvailable(lastName), "Unable to create a visitor without phone number");

        //Edit - remove phone number
        uDB.clickVisitor(lastName);
        uDB.removePhoneNumberVisitor();
        uDB.clickSave();

        //verify Error Message
        Assert.assertTrue(
                uDB.isAccessErrorMessageAvailable(),
                "Unable to find access error message.");

    }


    /*
Edit Visitor with Phone Number and access, Edit - remove phone number  and Access, User should be able to save.
*/
    @Test(priority = 12)
    public void verify_UpdateVisitor_RemoveAccessAndPhoneNumber() {

        // Edit - remove phone number  and Access
        uDB.show("Visitors");
        uDB.clickVisitor(lastName);
        uDB.remove_UAL_Outputs_Only();
        uDB.removePhoneNumberVisitor();
        uDB.clickSave();

        Assert.assertFalse(
                uDB.isAccessErrorMessageAvailable(),
                "Access error message is observed");

    }


    /*
Edit Visitor who does not have Phone Number and access, Add Access only, User should not be able to save.
*/
    @Test(priority = 13)
    public void verify_UpdateVisitor_AddAccessOnly() {

        // Edit Visitor - Add Access only
        uDB.show("Visitors");
        uDB.clickVisitor(lastName);
        uDB.addAccess("UAL - Outputs Only");
        uDB.clickSave();

        Assert.assertTrue(
                uDB.isAccessErrorMessageAvailable(),
                "Unable to find access error message.");

    }


    /*
Edit Resident who does not have Phone Number and access, Add Access and Phone Number, User should be able to save.
*/
    @Test(priority = 14)
    public void verify_UpdateVisitor_AddAccessAndPhone() {

        // edit visitor - Add Access and Phone Number
        uDB.show("Visitors");
        uDB.clickVisitor(lastName);
        uDB.addAccess("UAL - Outputs Only");
        uDB.addRandomPhoneNumberVisitor(phoneNumberVisitor);
        uDB.clickSave();

        Assert.assertFalse(
                uDB.isAccessErrorMessageAvailable(),
                "Access error message is observed");

        //Test Data clean up
        uDB.clickVisitor(lastName);
        uDB.deleteVisitorAndVerify(lastName + " " + firstName, lastName);
    }


    @AfterMethod
    public void tearDown() {
        log.info("Selenium Web Driver session is being terminated");
        driver.quit();
    }
}
