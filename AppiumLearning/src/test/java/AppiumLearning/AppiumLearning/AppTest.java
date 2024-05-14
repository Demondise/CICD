package AppiumLearning.AppiumLearning;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppTest extends BaseTest{
	@Test
	public void BasicTest() throws MalformedURLException, URISyntaxException {
				// xpath,id, - BY, AndroidUIAutomater,accessibiltyid ,className -AppiumBy
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]\n")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String popupMsg = driver.findElement(By.id("android:id/alertTitle")).getText();
		assertTrue(popupMsg.equals("WiFi settings"));
		driver.findElement(By.id("android:id/edit")).sendKeys("Shivam WIFI");
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();

	}
	@Test
	public void longPressGesture() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
		longPress(ele);
		String gestureMenu = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Sample action\"]")).getText();
		assertEquals(gestureMenu, "Sample action");
	}
	@Test
	public void scrollGesture() {
	driver.findElement(AppiumBy.accessibilityId("Views")).click();
//	scrollUntilEnd();
	scrollUntilText("WebView");
	}	
	@Test
	public void swipeGesture() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
		WebElement ele = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
		swipeToElement(ele, "left");
		assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[2]")).getAttribute("focusable"),"true");
		
	}
	@Test
	public void dragDropGesture() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		WebElement ele = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		dragDrop(ele, 650, 570);
		String confText = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
		assertEquals(confText, "Dropped!");
		
	}
	@Test
	public void Micellenious() {
		//Jump directly to testing page on android(Activity)
		//adb shell dumpsys window | grep -E 'mCurrentFocus' -MAC, adb shell dumpsys window | find "mCurrentFocus"
		Activity activity =  new Activity("io.appium.android.apis","io.appium.android.apis.preference.PreferenceDependencies");
		//driver.startActivity(activity); deprecated
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
			    "intent", "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"
			));
//		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
//		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]\n")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		//Rotation of device to landscape
		DeviceRotation landscape =new DeviceRotation(0,0,90);
		driver.rotate(landscape);
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String popupMsg = driver.findElement(By.id("android:id/alertTitle")).getText();
		assertTrue(popupMsg.equals("WiFi settings"));
		//Clip board check
		driver.setClipboardText("Shivam WIFI");
		driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
		//Emulate keys in android 
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.findElement(By.id("android:id/button1")).click();
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
	}
}
	
