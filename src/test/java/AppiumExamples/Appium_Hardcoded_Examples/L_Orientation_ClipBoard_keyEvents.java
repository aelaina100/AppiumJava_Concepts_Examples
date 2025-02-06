package AppiumExamples.Appium_Hardcoded_Examples;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class L_Orientation_ClipBoard_keyEvents extends Base {     //lecture #40
	// For the below test case, After step # 3:
	
	  // I have a requirement to replicate it with landscape mode (Turning it at 90-degree).
	 // The default mode is "portrait Mode".
	 // This is done in the second method/test case in here:
	
	/*
	 *******************  To use the screen in landscape mode (Turning it at 90-degree), there is one class available in Appium:*************
		
		                          DeviceRotation landScape= new DeviceRotation(0, 0, 90);
		                          driver.rotate(landScape); // rotate in landscape mode.
	   
	  Note: Sometimes, when you're in a certain mode, some functionalities might not work. Hence, switching to a different mode maybe required
	        . Also, some test cases are required to be re-executed on a different mode      */
	
	@Test(enabled=false)
	public void landScapeMode() throws MalformedURLException, URISyntaxException, InterruptedException
	{
        selectEmulator_launch_apiDemosApp();
		//1- Below: Click on the 'Preference' button
		driver.findElement(AppiumBy.accessibilityId("Preference")).click(); 
        
		//2- Below: Click on the "preference Dependencies"
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click(); 
	
		//3- Below: Click on the check box of 'WIFI' (After that, "WiFi settings" element becomes enabled
		 driver.findElement(By.id("android:id/checkbox")).click(); 
		 
		// To use the screen in landscape mode (Turning it at 90-degree), there is one class available in Appium: =================> Rotating code below:
		DeviceRotation landScape= new DeviceRotation(0, 0, 90);
		driver.rotate(landScape); // rotate in landscape mode.
			
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
		Thread.sleep(3000L); 
	}
	
	@Test(enabled =false)
	public void clipBoardFunctionalityTest() throws MalformedURLException, URISyntaxException, InterruptedException
	{
		// On mobile, one can copy the content to a clip board: [[ Simply in a field, when one types something and then copies it ]]
		 // So, is this 'copy' feature functioning properly ? (= Can you paste it ?)
		
		// In Android, when you copy text, you'd get the msg 'Copied to clip board !" after the text is copied into the virtual clip board.
		// This feature needs to be tested on the app level !
		
		selectEmulator_launch_apiDemosApp();
		driver.findElement(AppiumBy.accessibilityId("Preference")).click(); 
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click(); 
	    driver.findElement(By.id("android:id/checkbox")).click(); 
		//DeviceRotation landScape= new DeviceRotation(0, 0, 90);
		//driver.rotate(landScape); // rotate in landscape mode.
		// Thread.sleep(1000L); // No need for it as an implicit wait has already been set in base file.
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\").instance(1)")).click();
		
		Thread.sleep(2000L);  //view slowly
		driver.setClipboardText("some clip board text using .setClipboardText()"); // the argument is now copied into clip board.
		Thread.sleep(2000L); //view slowly
		driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
		Thread.sleep(5000L); //view slowly
	  
}
	
	@Test
	public void systemKeyEvents() throws MalformedURLException, URISyntaxException, InterruptedException
	{
		// Testing Android key events (System related & app. independent):==>  
				// Back button, Home button, type in a text using the keyboard, pressing 'Enter' in keyboard
				// are indeed possible with Appium !
				/*
				 * 
				 * driver.presskey(new KeyEvent(AndroidKey.  ) // and all the keys will be present !
				 * 
				 * Examples:
				 * 
				 * 
				 * driver.pressKey(new KeyEvent(AndroidKey.ENTER)); //pressing 'ENTER' in keyboard.
				 * driver.pressKey(new KeyEvent(AndroidKey.BACK)); // To take you one page back.
	               driver.pressKey(new KeyEvent(AndroidKey.HOME)); // To press on the 'Home' button of the phone so you're navigated away from the app.
				 * 
				 * VERY IMPORTANT: To hide keyboard:  driver.hideKeyboard();
				 */
		
		selectEmulator_launch_apiDemosApp();
		driver.findElement(AppiumBy.accessibilityId("Preference")).click(); 
		driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click(); 
		
		Thread.sleep(2000L);  // to observe
		//Below: Click on the check box of 'WIFI' (After that, "WiFi settings" element becomes enabled
	    driver.findElement(By.id("android:id/checkbox")).click(); 
	    
	   // Clicking on "WiFi settings" element (After that, the pop-up of 'WiFi settings" appear)
		 driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\").instance(1)")).click();
		 
		Thread.sleep(2000L);  //view slowly
		driver.setClipboardText("some clip board text using .setClipboardText()"); // the argument is now copied into clip board.
		Thread.sleep(2000L); //view slowly
		
		driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
		Thread.sleep(5000L); //view slowly
	  
		driver.pressKey(new KeyEvent(AndroidKey.ENTER)); //pressing 'ENTER' in keyboard.
		Thread.sleep(2000L);
	    
		// Clicking on 'Ok' in the pop-up.
		 driver.findElement(By.id("android:id/button1")).click();
	    
	    driver.pressKey(new KeyEvent(AndroidKey.BACK)); // To take you one page back.
	    Thread.sleep(2000L);  // to observe
	    
	    driver.pressKey(new KeyEvent(AndroidKey.HOME)); // To press on the 'Home' button of the phone so you're navigated away from the app.
	    Thread.sleep(2000L);  // to observe before app abruptly quits.
	}
		
		
	
	
	
 }