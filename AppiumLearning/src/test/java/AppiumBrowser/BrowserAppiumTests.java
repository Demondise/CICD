package AppiumBrowser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class BrowserAppiumTests extends BaseTestBrowser {
	@Test
	public void webSite() {
		driver.get("https://www.rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("//button[@class='navbar-toggler']")).click();
		driver.findElement(By.xpath("//a[@routerlink='/products']")).click();
		((JavascriptExecutor)driver).executeScript("Window.scrollBy(0,1000)", "");
		driver.findElement(By.xpath("//a[@text='Devops']")).click();
	}
}
