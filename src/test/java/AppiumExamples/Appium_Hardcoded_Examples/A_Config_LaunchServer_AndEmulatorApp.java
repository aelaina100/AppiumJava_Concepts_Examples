package AppiumExamples.Appium_Hardcoded_Examples;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class A_Config_LaunchServer_AndEmulatorApp { 
	
	@Test(enabled=false)
	public void A_LaunchServer_AndEmulatorApp1() throws MalformedURLException, URISyntaxException
	{
		/* Pre-conditions:
		   1-Manually start the Appium server (So that, it is 'listening').
		   2- Launch Android Studio.
		   3- Start the virtual device you want to run this script on.
		   4- Include the android .apk installer under a 'resources' package.   
		   [If any of these 4 steps isn't performed, script will throw an exception].
		   
		   [Note: The first 3 steps will be automated as shown in the next method/test case in this .java class file..The purpose here 
		   is to show the novice how things are configured/ to give understanding on the fundamentals ].
		  */
		
		// "AndroidDriver" class to automate an Android App:
		UiAutomator2Options options= new UiAutomator2Options();
		options.setDeviceName("AhmadDevice"); // From Android Studio, where I created an emulator.
		options.setApp("\\Users\\AE\\eclipse-workspace\\Appium_Hardcoded_Examples\\src\\test\\java\\resources\\ApiDemos-debug.apk"); 
		//Above: Project-embedded Installer path. App will be installed on emulator EVERY SINGLE TIME THIS LINE IS EXECUTED. Path will be made dynamic in future.
		//Plus another 'options' step for specifying device version IF you have another emulator with the same device name. (In doubt of this comment !!)
		
		AndroidDriver driver= new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options); //URL Syntax as of Java 17.Line launches app
		// 1st argument: URL +Port of the Appium Server, that's currently running: http://127.0.0.1:4723 (Fixed local machine IP address)
		// & If in doubt, consult the logs when launching the Appium server.
		//2nd argument: Which specific device in Android Studio, this script will run on ?
		
		driver.quit(); // to close the App in the emulator.
		//Now, all related logs will be displayed in the running Appium server Node.js command prompt.
	}

	
	
	
    // Below method/ test case is to start & stop Appium Server programmatically using AppiumServerBuilder (Instead of launching it manually):
	
		/* 'AppiumServiceBuilder' is the class to do so.
		 * 		Whenever you manually start Appium Server, a 'main.js' file is triggered.
		 *      So, one needs to give this class, the path of the "main.js". PLUS the IP address. PLUS port (where Appium Server usually runs)
		 *      
		 *      Appium Server logs will be shown in Eclipse.
		 */
	@Test(enabled=true)
	public void  A_LaunchServer_AndEmulatorApp2() throws MalformedURLException, URISyntaxException
	{   
		//Starting the Appium Server programmatically:
		AppiumDriverLocalService service= new AppiumServiceBuilder().withAppiumJS(new File("\\Users\\AE\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build(); //IP:Port  If in doubt, consult the logs when MANUALLY launching the Appium server via node.js cmd
		service.start(); // Start this instance (Start the Appium Server)
		
		// "AndroidDriver" class to automate an Android App:
		UiAutomator2Options options= new UiAutomator2Options();
		options.setDeviceName("AhmadDevice"); // From Android Studio, where I created an emulator.
		options.setApp("\\Users\\AE\\eclipse-workspace\\Appium_Hardcoded_Examples\\src\\test\\java\\resources\\ApiDemos-debug.apk"); 
		
		AndroidDriver driver= new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options); //URL Syntax as of Java 17.Line launches app
		
		driver.quit();  // Close the app in the emulator.
		service.stop(); // Stopping the Appium Server. Port '4723' will no longer be blocked.  
		
		
	}
	
	
	
	
	
}
