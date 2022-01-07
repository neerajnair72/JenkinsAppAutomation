import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AppAutomation {
    public static String userName = System.getenv("LT_USERNAME");
    public static String accessKey = System.getenv("LT_ACCESS_KEY");
    public static AppiumDriver driver;
    public String gridURL = "@beta-hub.lambdatest.com/wd/hub";
    // private String Status = "failed";

    @org.testng.annotations.Parameters(value = {"platformName", "deviceName", "platformVersion"})
    @BeforeMethod
    public void setup( String platformName, String deviceName, String platformVersion) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build","SmokeTestAPP");
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("platformVersion",platformVersion);
        capabilities.setCapability("app", "lt://APP10020521641460986975126");

        capabilities.setCapability("deviceOrientation", "PORTRAIT");
        capabilities.setCapability("console",true);
        capabilities.setCapability("network",true);
        capabilities.setCapability("visual",true);


//        driver = new RemoteWebDriver(new
//                URL("https://"+userName+":"+accessKey+"driver = new RemoteWebDriver(new URL(hub), caps);"), capabilities);
       driver = new AndroidDriver(new URL("https://"+userName+":"+accessKey+"@beta-hub.lambdatest.com/wd/hub"), capabilities);
    }
    @Test
    public void basicTest() {
        try {
            AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                    ExpectedConditions.elementToBeClickable(MobileBy.id("Search Wikipedia")));
            searchElement.click();
            AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                    ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
            insertTextElement.sendKeys("LambdaTest" + Keys.ENTER);
            Thread.sleep(5000);
            List allProductsName = driver.findElementsByClassName("android.widget.TextView");
            assert (allProductsName.size() > 0);
//            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        } catch (Exception e) {

//            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
            e.printStackTrace();
        }/*catch (AssertionError a){
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
            a.printStackTrace();
        }*/
// The driver.quit statement is required, otherwise the test continues to execute, leading to timeout
    }
    @AfterMethod
    public void tearDown() {
        //driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }
}
