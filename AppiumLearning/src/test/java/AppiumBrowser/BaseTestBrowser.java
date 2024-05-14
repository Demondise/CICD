package AppiumBrowser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTestBrowser {
AndroidDriver driver;
@BeforeMethod
public void configureAppium() throws MalformedURLException, URISyntaxException {
 
 UiAutomator2Options options = new UiAutomator2Options();
 options.setChromedriverExecutable("/home/sustenance/Downloads/chromedriver_linux64/chromedriver");
 options.setDeviceName("ShviamDevice");
 options.setCapability("browserName", "Chrome");
 driver = new AndroidDriver(new URI("http://127.0.0.1:4723/wd/hub").toURL(),options);
}
@AfterMethod
public void tearDown() {
 driver.quit();
  
}
}
