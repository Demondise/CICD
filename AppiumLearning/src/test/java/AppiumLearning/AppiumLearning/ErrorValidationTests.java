package AppiumLearning.AppiumLearning;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTests extends BaseTest{
	@Test
	public void fillNameError() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement element =  driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry"));
		element.click();
		driver.hideKeyboard();
		scrollUntilText("Argentina");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Shivam Sharma");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		//Toast Message in android comes with android.widgit.Toast
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
	    Assert.assertEquals(toastMessage,"Please enter your name");
	}
}
