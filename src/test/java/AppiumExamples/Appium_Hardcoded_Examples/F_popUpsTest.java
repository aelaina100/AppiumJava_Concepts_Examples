package AppiumExamples.Appium_Hardcoded_Examples;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class F_popUpsTest extends Base {
	
	
	@Test
	public void popUps() throws MalformedURLException, URISyntaxException, InterruptedException
	{
		selectEmulator_launch_apiDemosApp();
		
		//1- Below: Click on the 'Preference' button
		driver.findElement(AppiumBy.accessibilityId("Preference")).click(); 
        
		//2- Below: Click on the "preference Dependencies"
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click(); 
	
		//3- Below: Click on the check box of 'WIFI' (After that, "WiFi settings" element becomes enabled
		 driver.findElement(By.id("android:id/checkbox")).click(); 
		 
		// Thread.sleep(1000L); // No need for it as an implicit wait has already been set in base file.
		 
		//4- Clicking on "WiFi settings" element (After that, the pop-up of 'WiFi settings" appear)
		 driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\").instance(1)")).click();
		 
		 //5- Identifying the field in the pop-up and sending keys.
		 driver.findElement(By.id("android:id/edit")).sendKeys("Learning Appium tool for mobile automation");
		
		  
		//6- Extracting the text of the pop-up
		 String expectedText= "WiFi settings".toLowerCase().trim(); // assuming, in future, that text will become case sensitive and/or have space(s)
		 String popUpText= driver.findElement(By.id("android:id/alertTitle")).getText().toLowerCase().trim();
		// or use (less reliable):
		// String popUpText= driver.findElement(By.xpath("//android.widget.TextView[@resource-id= 'android:id/alertTitle']")).getText().toLowerCase().trim();
		 Assert.assertEquals(popUpText, expectedText);
		 
		 
		//7- Clicking on 'Ok' in the pop-up.
		 driver.findElement(By.id("android:id/button1")).click();
		 //or below: Just to show you different locator technique (The 'id' one is far superior though & what should be used).
		//driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
		
		 }
}
