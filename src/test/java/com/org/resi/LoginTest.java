package com.org.resi;

import com.org.resi.pages.LoginPage;
import com.org.resi.utilities.SeleniumUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {

  public static Logger log = LogManager.getLogger();
  private WebDriver driver = null;
  private LoginPage lp;
  private SeleniumUtil seleniumUtil = null;

  @BeforeMethod
  @Parameters({"url", "browser", "browserVersion", "targetRun" , "data"})
  public void login(String url, String browser, String browserVersion, String targetRun , String data) {

    seleniumUtil = new SeleniumUtil();

    // Creating driver object based on browser stack configuration
    if (targetRun.equalsIgnoreCase("browserStack")) {
      driver = seleniumUtil.createBrowserStackDriver(url,browser,browserVersion);
    } else if (targetRun.equalsIgnoreCase("local")) {
      driver = seleniumUtil.createDriver(url,browser);
    } else {
      log.info("Invalid `runOn` value present in testNG_TechciergeStage.xml");
      throw new SkipException("Skipping tests");
    }

    log.info("Selenium Web Driver session initiated on -" + targetRun);

    lp = new LoginPage(driver ,data);

  }

  @Test(priority = 1)
  public void validateLogin() {

     lp.logIn();
     lp.selectCompany();
     lp.navigateToUnitDB();

  }

  @AfterMethod
  public void tearDown() {
    log.info("Selenium Web Driver session is being terminated");
   driver.quit();
  }
}
