package AppiumLearning.AppiumLearning;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	public AndroidDriver driver;
	AppiumDriverLocalService service;
	public void longPress(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(), "duration", 2000
			));
	}
	public void scrollUntilEnd() {
		boolean canScrollMore;
		do {
			 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				    "left", 100, "top", 100, "width", 200, "height", 200,
				    "direction", "down",
				    "percent", 3.0
				));
			}while(canScrollMore);
	}
	public void scrollUntilText(String text){
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));

	}
	public void swipeToElement(WebElement ele, String Direction) {
		
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
			    "direction", Direction,
			    "percent", 0.1
			));
	}
	public void dragDrop(WebElement ele, int x, int y) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "endX", x,
			    "endY", y
			));
	}
@BeforeMethod
public void configureAppium() throws MalformedURLException, URISyntaxException {
//	 service = new AppiumServiceBuilder()
//			.withAppiumJS(new File("/lib/node_modules/appium/build/lib/main.js")).withIPAddress("0.0.0.0")
//			.usingPort(4993).build();
	 UiAutomator2Options options = new UiAutomator2Options();
     //service.start();
	 options.setChromedriverExecutable("C:\\Users\\shivam_shar\\Downloads\\chromedriver.exe");
     options.setDeviceName("ShivamMob");
     options.setApp("C:\\Users\\shivam_shar\\eclipse-workspace\\AppiumLearning\\src\\test\\java\\resources\\General-Store.apk");
     driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(),options);
}
@AfterMethod
public void tearDown() {
	 driver.quit();
     //service.stop();
      
	}
}
