package AppiumExamples.Appium_Hardcoded_Examples;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


// A base class (To start Appium Server & launch the emulator [AhmadDevice] to automate app [ApiDemos]) of our test cases.
public class Base{
	
	public  AndroidDriver driver;
	public  AppiumDriverLocalService service;
	
	@BeforeClass   
	public  void startAppiumServer() 
	{
	    service= new AppiumServiceBuilder().withAppiumJS(new File("\\Users\\AE\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build(); 
		service.start(); 	
	}
	
	
	
	public  void selectEmulator_launch_apiDemosApp() throws MalformedURLException, URISyntaxException // native app
	{
		// "AndroidDriver" class to automate an Android App:
				UiAutomator2Options options= new UiAutomator2Options();
				options.setDeviceName("AhmadDevice");
				options.setApp("\\Users\\AE\\eclipse-workspace\\Appium_Hardcoded_Examples\\src\\test\\java\\resources\\ApiDemos-debug.apk"); 
			    driver= new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options); }
	
	
	public  void selectEmulator_launch_generalStoreApp() throws MalformedURLException, URISyntaxException // a hybrid app
, InterruptedException
	{
		// "AndroidDriver" class to automate an Android App:
				UiAutomator2Options options= new UiAutomator2Options();
				options.setDeviceName("AhmadDevice");
				options.setApp("C:\\Users\\AE\\eclipse-workspace\\Appium_Hardcoded_Examples\\src\\test\\java\\resources\\General-Store.apk"); 
			    driver= new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options); }
	
	// Add a future method to select a simulator and launch a specific app on it.
	
	
	
	
	@AfterMethod
	public void quitApp() throws InterruptedException 
	{
		Thread.sleep(10000L); // for debugging only (Want to see the execution slowly before abruptly exiting the application)
		driver.quit();  // Close the app in the emulator.
	}

	
	
	@AfterClass
	public void stopAppiumServer()
	{
		service.stop();   // Stopping the Appium Server. Port '4723' will no longer be blocked   
	}
     
	
	
	public void longPressing(WebElement element)
	{
		
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
		    "elementId", ((RemoteWebElement) element).getId(), "duration", 2000  
		));  // long press for 2 seconds.
	}
	
	
	
	public void scrollWithAndroidUiAutomator(String text) throws MalformedURLException, URISyntaxException, InterruptedException
	{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
	
	//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));")); // hardcoded with some value
	Thread.sleep(2000L);   //To view things slowly before app closes.
	}
	
	
	
	public void scrollWithJavaScriptExecutor() throws MalformedURLException, URISyntaxException, InterruptedException
	{
	@SuppressWarnings("unused")
	Boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
		    "left", 100, "top", 100, "width", 200, "height", 200,
		    "direction", "down",
		    "percent", 2.0  //percent: The size of the scroll as a percentage of the scrolling area size
		)); } 
	
	
	
	public void swiping(WebElement element, String direction, double d)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId",  ((RemoteWebElement) element).getId(),     //((RemoteWebElement) element).getId()
		        "direction", direction,
		        "percent", d
		));
		
	}
	
	
	public void dragAndDrop(WebElement element, int endX, int endY)
	{
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
	
	        "elementId", ((RemoteWebElement) element).getId(),
	        "endX", endX,   // Values come from the Appium Inspector utilizing the option of "Tab/Swipe by coordinates" [Center coordinates of target where element is dropped].
	        "endY", endY
	    ));  // Try  x669 y1011...x644 y584
	}
}
	

	
	
	

