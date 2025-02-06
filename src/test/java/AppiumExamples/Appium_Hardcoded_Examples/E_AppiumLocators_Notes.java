package AppiumExamples.Appium_Hardcoded_Examples;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;


/*                   ************************************** Crucial Appium locator-related notes: **********************************
 * 
 * For the xpath of:   	//android.widget.TextView[@content-desc="Preference"]  **********************************************
 * 	
 *		When pasting it into: driver.findElement(By.xpath(""))
 *      one would get:    driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]"))
 *      
 *      In this case: You can either ignore the automatically generated double quotes and forward slashes.
 *      Or: remove the slashes and convert the double quotes into single ones.  (Both work just fine)
 *      
 *      
 *      
 *      
 *      HOWEVER; For the androidUIAutomator of:   new UiSelector().text("3. Preference dependencies")  *************************************
 *      
 *      Just use it as it is ! [do NOT remove the automatically generated slashes and/or convert the double quotes into single ones] 
 *      As this will render the exception of "org.openqa.selenium.InvalidSelectorException"
 *     
 *      So, When simply pasting it, one would get:
 *      driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"3. Preference dependencies\")")).click();   // works!
 *      ******************************************************************************************************************************
 *      
 *       Note: use:       AppiumBy.className  instead of   By.className  as the latter is rendering issues &
 *                        & might break in future Appium library versions !
 *                        
 *       Final Note: With xpaths, your method/test case won't be stable. Hence, if nothing can be used but an xpath, consult with developers 
 *                   so that id  & accessibility id (preferred) can be provided.
 *                           
 *                           
 */


public class E_AppiumLocators_Notes extends Base {
	
	
		// Below: Clicking on the "Preference" button >> "Preference Dependencies" >> Check box of "WiFi"
		@Test
		public void navigateToPreferenceButtons( ) throws MalformedURLException, URISyntaxException, InterruptedException 
		{
			selectEmulator_launch_apiDemosApp();
			//1- Below: Click on the 'Preference' button
			driver.findElement(AppiumBy.accessibilityId("Preference")).click(); 
            
			//2- Below: Click on the "preference Dependencies"
			driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click(); //works
			// Below: Just to show the different locator techniques that can be used  (xpath is not as reliable as: id, accessibilityId, class, AndroidUIAutomator)
			//driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();  //works
			// driver.findElement(By.xpath("(//android.widget.TextView[@resource-id= 'android:id/text1'])[3]")).click();  //works
			//driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"3. Preference dependencies\")")).click(); // works!!		
			// CRUCIAL:  androidUIAutomator locator has the original value of:   new UiSelector().text("3. Preference dependencies")
			
			//3- Below: Click on the check box of 'WIFI'
			 driver.findElement(By.id("android:id/checkbox")).click(); // works !
			// Or: Any of the following lines below:
			//driver.findElement(AppiumBy.className("android.widget.CheckBox")).click(); // works !
			//driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"android:id/checkbox\")")).click(); // works
			//driver.findElement(By.xpath("//android.widget.CheckBox")).click();  // works !
			
			Thread.sleep(3000L);  // Wait for 3 seconds before app closes in emulator 
			                      // as:  @AfterMethod  public void quitApp() is executed after this wait (Situated
			                      //  in the base class).
			
		// Note: In all of the above 3 steps, once could utilize an instance xpath locator techniques as well ( once again,
			// not as reliable as Id, AccessibilityId, className, AndroidUIAutomator or even a regular xpath with no instance technique.
		}
		
		

}
