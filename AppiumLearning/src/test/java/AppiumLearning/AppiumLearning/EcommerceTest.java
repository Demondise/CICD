package AppiumLearning.AppiumLearning;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.context.Context;
public class EcommerceTest extends BaseTest {

	@Test
	public void addToCart() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement element =  driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry"));
		element.click();
		driver.hideKeyboard();
		scrollUntilText("Argentina");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Shivam Sharma");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		scrollUntilText("Jordan 6 Rings");
        int count = driver.findElements(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id=\"com.androidsample.generalstore:id/rvProductList\"]/android.widget.RelativeLayout")).size();
      for(int i = 1;i<count;i++) {
    	  String text = driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\"])["+i+"]")).getText();
    	  if(text.equals("Jordan 6 Rings")) {
    		  driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])["+i+"]")).click();
    		  break;
    	  }
      }
      	driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
      	WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
      	By pageTitle = By.id("com.androidsample.generalstore:id/toolbar_title");
      	w.until(ExpectedConditions.attributeToBe(pageTitle, "text", "Cart"));
      	WebElement find = driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\"]")).stream().filter(s->s.getText().equals("Jordan 6 Rings")).findFirst().orElse(null);
      	assertTrue(find!=null);
      	driver.findElement(By.className("android.widget.CheckBox")).click();
      	WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
      	longPress(ele);
      	driver.findElement(By.id("android:id/button1")).click();
      	driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
      	Thread.sleep(5000);
	}
	@Test
	public void sumCheck() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement element =  driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry"));
		element.click();
		driver.hideKeyboard();
		scrollUntilText("Argentina");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Shivam Sharma");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		List<WebElement> lil = driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']"));
		lil.get(0).click();
		lil.get(1).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
      	WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
      	By pageTitle = By.id("com.androidsample.generalstore:id/toolbar_title");
      	w.until(ExpectedConditions.attributeToBe(pageTitle, "text", "Cart"));
      	double sum = 0;
      	List<WebElement> ls = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice']"));
      	for(int i=0;i<ls.size();i++) {
      		sum+=Double.parseDouble(ls.get(i).getText().substring(1));
      	}
      	Double givenSum = Double.parseDouble(driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/totalAmountLbl']")).getText().substring(2));
      	assertEquals(sum, givenSum);
	}
	@Test
	public void hybridApp() throws Exception {
		addToCart();
		Set<String> contextHandles =  driver.getContextHandles();
		contextHandles.stream().forEach(s->System.out.println(s));
		Thread.sleep(4000);
		driver.context("WEBVIEW_com.androidsample.generalstore");
		
		driver.findElement(By.name("q")).sendKeys("Shivam Sharma");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
	}
}
 