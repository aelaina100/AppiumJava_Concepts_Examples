package AppiumExamples.Appium_Hardcoded_Examples;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


/* In this example, we'll use the "Appium Inspector" tool to get the properties associated with each single element in the mobile App.
 * Instructions: [It's encouraged to sequentially follow the below steps in order to  avoid common errors/ malfunctions 
 *                associated with the use of Appium Inspector while building a method/ test case]
 * 
 * 				1- For the desired virtual device, 'Cold Boot' it. Where:
 *                 -If this option is disabled (Device is still running), then stop/ shut it down (Click on the red square). Now, click on the enabled 'Cold Boot'
 *              4- So, Android studio will establish a fresh connection to the virtual device. Wait till device displays the fully loaded home screen.
 *              5- Launch the desired App.
 *              6- Then, manually start the Appium Server.
 *              7- And then, launch "Appium Inspector" & start the session for the corresponding virtual device.
 */

public class B_Config_LocateElementsEmulatorBasics {
	
	// In the app, click on "Preference".
	@Test
	public void firstAppium() throws MalformedURLException, URISyntaxException
	{
	
	        //Starting the Appium Server programmatically:
			AppiumDriverLocalService service= new AppiumServiceBuilder().withAppiumJS(new File("\\Users\\AE\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
					.withIPAddress("127.0.0.1").usingPort(4723).build(); 
			service.start(); 
			// "AndroidDriver" class to automate an Android App:
			UiAutomator2Options options= new UiAutomator2Options();
			options.setDeviceName("AhmadDevice");
			options.setApp("\\Users\\AE\\eclipse-workspace\\Appium_Hardcoded_Examples\\src\\test\\java\\resources\\ApiDemos-debug.apk"); 
			AndroidDriver driver= new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options); 
			
			driver.findElement(AppiumBy.accessibilityId("Preference")).click();
			/* Notes:
			 * 
			 * driver.findElement(By. )      <==== Here,  By. locator methods come from Selenium jars + Appium jars.
                                     
                                       [This is because Appium is nothing but Selenium PLUS additional capabilities 
                                       that support mobile gestures. Nevertheless, only the types of locators Appium supports are usable]
                                       


               driver.findElement(AppiumBy. ) <==== Here,  AppiumBy. locator methods come from Appium jars ONLY.
			 *                                     ["AppiumBy." is  used for Appium Specific locators: accessibilityId & androidUIAutomator]
			 *                                     
			 *    **** Types of locators Appium supports FOR ANDROID:    id,  accessibilityId,  className,  androidUIAutomator,  Xpath ****
			 * 
			 * 
			 */
			driver.quit();  // Close the app in the emulator.
			service.stop(); // Stopping the Appium Server. Port '4723' will no longer be blocked.  

}
	
}
