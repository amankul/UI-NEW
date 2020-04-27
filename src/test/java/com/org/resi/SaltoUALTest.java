package com.org.resi;

import com.org.resi.pages.LoginPage;
import com.org.resi.pages.UnitDBPage;
import com.org.resi.utilities.SeleniumUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.time.LocalDate;

public class SaltoUALTest {

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

    /*
    Create Resident add UAL access and verify that all UAL's added are present in the edit resident window
     */
    @Test(priority = 1)
    public void verify_AddResidentWithUAL() {

        // Create Resident With UAL's
        uDB.show("Occupants");
        uDB.clickAddNew();
        uDB.selectUnitNumber("Automat");
        uDB.sendFirstAndLastName(firstName, lastName);
        uDB.selectLanguage("English");
        uDB.selectMoveIn_MoveOutDates();
        uDB.addRandomPhoneNumber(phoneNumber);
        uDB.addAccess("UAL - Outputs Only");
        uDB.addAccess("UAL - Doors & Zones only");
        uDB.addAccess("UAL - Doors,Zones & Outputs");
        uDB.addAccess("UAL - SD Online Doorz");
        uDB.clickSave();

        // Tap On Created Resident
        uDB.clickResident(phoneNumber);

        // UAL validations
        Assert.assertTrue(
                uDB.isAccessPresent_UAL_OutputsOnly(),
                "UAL Outputs only is not available for the Resident - AutomationResident");
        Assert.assertTrue(
                uDB.isAccessPresent_UAL_Doors_ZonesOnly(),
                "UAL Doors And Zones  only is not available for the Resident - AutomationResident");

        Assert.assertTrue(
                uDB.isAccessPresent_UAL_DoorsZones_And_Outputs(),
                "UAL Doors,Zones & Outputs is not available for the Resident - AutomationResident");
        Assert.assertTrue(
                uDB.isAccessPresent_UAL_SD_Online_Doors_only(),
                "UAL SD Online Doors  is not available for Resident - AutomationResident");

        uDB.clickSave();
    }

    /*
    Remove a UAL for the resident and verify it in the edit resident window
     */
    @Test(priority = 2)
    public void verify_Resident_RemoveUAL() {

        uDB.show("Occupants");

        // Tap On Created Resident
        uDB.clickResident(phoneNumber);

        // Removing UAL's
        uDB.remove_UAL_Outputs_Only();
        uDB.clickSave();

        // Tap On Created Resident
        uDB.clickResident(phoneNumber);

        // UAL validations
        Assert.assertFalse(
                uDB.isAccessPresent_UAL_OutputsOnly(),
                "UAL Outputs only is not Removed for the Resident " + lastName + ", " + firstName);
    }

    /*
    ADD a UAL for the resident and verify it in the edit resident window
    */
    @Test(priority = 3)
    public void verify_Resident_AddUAL() {

        uDB.show("Occupants");

        // Tap On Created Resident
        uDB.clickResident(phoneNumber);

        // Adding UAL's
        uDB.addAccess("UAL - Outputs Only");
        uDB.clickSave();

        // Tap On Created Resident
        uDB.clickResident(phoneNumber);

        // UAL validations
        Assert.assertTrue(
                uDB.isAccessPresent_UAL_OutputsOnly(),
                "UAL Outputs only is not available for the Resident " + lastName + ", " + firstName);
    }

    /*
    Remove all UAL's for the resident and verify it in the edit resident window
    */
    @Test(priority = 4)
    public void verify_Resident_RemoveAllAccess() {

        uDB.show("Occupants");

        // Tap On Created Resident
        uDB.clickResident(phoneNumber);

        // Adding UAL's
        uDB.remove_All_Access(4);
        uDB.clickSave();

        // Tap On Created Resident
        uDB.clickResident(phoneNumber);

        // UAL validations
        Assert.assertTrue(
                uDB.isAllAccessRemoved(),
                "Found Access in access tab for the Resident - " + lastName + ", " + firstName);
    }

    /*
     Delete Resident who has an UAL Access
    */
    @Test(priority = 5)
    public void verify_Delete_Resident() {

        uDB.show("Occupants");

        // Tap On Created Resident
        uDB.clickResident(phoneNumber);

        // Adding UAL's
        uDB.addAccess("UAL - Doors & Zones only");
        uDB.clickSave();

        // Tap On Created Resident
        uDB.clickResident(phoneNumber);

        // UAL validations
        Assert.assertTrue(
                uDB.deleteResidentAndVerify(lastName + ", " + firstName),
                "Delete action failed on resident - " + lastName + ", " + firstName);
    }

    /*
    Create Visitor add UAL access and verify that all UAL's added are present in the edit resident window
     */
    @Test(priority = 6)
    public void verify_AddVisitorWithUAL() {

        // Create Visitor With UAL's
        uDB.show("Visitors");
        uDB.clickAddNewVisitor();
        uDB.selectVisitorUnitNumber("Automat");
        uDB.sendFirstAndLastNameVisitor(firstName, lastName);
        uDB.addRandomPhoneNumberVisitor(phoneNumberVisitor);
        uDB.addAccess("UAL - Outputs Only");
        uDB.addAccess("UAL - Doors & Zones only");
        uDB.addAccess("UAL - Doors,Zones & Outputs");
        uDB.addAccess("UAL - SD Online Doorz");
        uDB.clickSave();

        // Tap On Created Resident
        uDB.clickVisitor(lastName);

        // UAL validations
        Assert.assertTrue(
                uDB.isAccessPresent_UAL_OutputsOnly(),
                "UAL Outputs only is not available for the visitor - AutomationResident");
        Assert.assertTrue(
                uDB.isAccessPresent_UAL_Doors_ZonesOnly(),
                "UAL Doors And Zones  only is not available for the visitor - AutomationResident");

        Assert.assertTrue(
                uDB.isAccessPresent_UAL_DoorsZones_And_Outputs(),
                "UAL Doors,Zones & Outputs is not available for the visitor - AutomationResident");
        Assert.assertTrue(
                uDB.isAccessPresent_UAL_SD_Online_Doors_only(),
                "UAL SD Online Doors  is not available for visitor - AutomationResident");

        uDB.clickSave();
    }

    /*
    Remove a UAL for the visitor and verify it in the edit resident window
    */
    @Test(priority = 7)
    public void verify_Visitor_RemoveUAL() {

        uDB.show("Visitors");

        // Tap On Created visitor
        uDB.clickVisitor(lastName);

        // Removing UAL's
        uDB.remove_UAL_Outputs_Only();
        uDB.clickSave();

        // Tap On Created visitor
        uDB.clickVisitor(lastName);

        // UAL validations
        Assert.assertFalse(
                uDB.isAccessPresent_UAL_OutputsOnly(),
                "UAL Outputs only is not Removed for the visitor " + lastName + ", " + firstName);
    }

    /*
    ADD a UAL for the visitor and verify it in the edit resident window
    */
    @Test(priority = 8)
    public void verify_Visitor_AddUAL() {

        uDB.show("Visitors");

        // Tap On Created visitor
        uDB.clickVisitor(lastName);

        // Adding UAL's
        uDB.addAccess("UAL - Outputs Only");
        uDB.clickSave();

        // Tap On Created visitor
        uDB.clickVisitor(lastName);

        // UAL validations
        Assert.assertTrue(
                uDB.isAccessPresent_UAL_OutputsOnly(),
                "UAL Outputs only is not available for the visitor " + lastName + ", " + firstName);
    }

    /*
    Remove all UAL's for the visitor and verify it in the edit resident window
    */
    @Test(priority = 9)
    public void verify_Visitor_RemoveAllAccess() {

        uDB.show("Visitors");

        // Tap On Created visitor
        uDB.clickVisitor(lastName);

        // Adding UAL's
        uDB.remove_All_Access(4);
        uDB.clickSave();

        // Tap On Created visitor
        uDB.clickVisitor(lastName);

        // UAL validations
        Assert.assertTrue(
                uDB.isAllAccessRemoved(),
                "Found Access in access tab for the visitor - " + lastName + ", " + firstName);
    }

    /*
    Delete visitor who has an UAL Access
    */
    @Test(priority = 10)
    public void verify_Delete_Visitor() {

        uDB.show("Visitors");

        // Tap On Created visitor
        uDB.clickVisitor(lastName);

        // Adding UAL's
        uDB.addAccess("UAL - Doors & Zones only");
        uDB.clickSave();

        // Tap On Created visitor
        uDB.clickVisitor(lastName);

        // UAL validations
        Assert.assertTrue(
                uDB.deleteVisitorAndVerify(lastName + " " + firstName, lastName),
                "Delete action failed on resident - " + lastName + " " + firstName);
    }

    @AfterMethod
    public void tearDown() {
        log.info("Selenium Web Driver session is being terminated");
        driver.quit();
    }
}
