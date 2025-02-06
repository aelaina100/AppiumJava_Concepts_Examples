package AppiumExamples.Appium_Hardcoded_Examples;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;


// The concept of launching the app directly on a specific page ( = The concept of appPackage & appActivity)
public class M_AppPackage_AppActivity_Concepts extends Base {
	
	/* Can one launch the app on a specific page instead of starting on the home page (Where they have to navigate a few steps to some target page) ?
	 *                                               The answer is YES
	 *          
	 * So the basic/default way of working with the app is:  To launch the app so you  1st land on the home page.
	 * This is done with the method of: selectEmulator_launch_apiDemosApp() situated in the base.java class:=============>
	 * 
	 *      
	 *      public  void selectEmulator_launch_apiDemosApp() throws MalformedURLException, URISyntaxException
	      {
		// "AndroidDriver" class to automate an Android App:
				UiAutomator2Options options= new UiAutomator2Options();
				options.setDeviceName("AhmadDevice");
				options.setApp("\\Users\\AE\\eclipse-workspace\\Appium_Hardcoded_Examples\\src\\test\\java\\resources\\ApiDemos-debug.apk"); 
			    driver= new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options); 
			    
          }
	
	 *  So, assume that there is a testing scenario that starts with the app on the 2nd page of "3.preference dependencies"
	 *  which is accessed from: 
	 *  Launch the app >> Preferences >>3.Preference dependencies.
	 *  
	 *    ********* So why don't we directly launch the app with this page: "3.preference dependencies" ? avoiding the navigation pages to it.
	 *    
	 *    To do that, provide VALUES for the 2 terminologies of:   App Package & App Activity
	 *               
	 *    App package:  is the package name.
	 *    an app activity: is some single page of the app. Hence, the app is a collection of app activitieS.
	 *    
	 *    	so now, we have to directly launch the app on the activity level of "3.preference dependencies" page.
	 *    
	 *      Implementation:=====================>
	 *      Activity activity= new Activity(null, null); 
	 *      // check its 'behind the scenes' code to understand what the arguments are for.
	 *      // conclusion: the arguments are:  String appPackage, String appActivity
	 *      // So how to provide these 2 ?
	 *      
	 *   A-   First step is to ensure that your device is actually running [Assuming that Android Studio is launched with the virtual device up and running]==>
	 *      	Launch the regular command line (Avoid powerShell)> Type in "adb devices" > You should get:    
	 *      
	 *         ""List of devices attached
                emulator-5554   device""
                
                Not seeing such a message denotes that your device isn't recognized (Virtual device in Android Studio NOT up & running).
             
          B-   Second Step: Get the value of "appActivity" by:
          		On the virtual device in Android Studio, navigate to the page of "3.preference dependencies"
          		> In regular cmd (Avoid PowerShell):   adb shell dumpsys window | find "mCurrentFocus"
          		
          		You will get something to the affect of:======>
          		 mCurrentFocus=Window{cce9a3c u0 io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies}
          		
          		So===> appPackage  (Before the slash) ==>  io.appium.android.apis
          		       appActivity (After the slash)  ==>  io.appium.android.apis.preference.PreferenceDependencies
	 *
	 * 
	 */
	
	
	@Test
	public void m() throws MalformedURLException, URISyntaxException, InterruptedException
	{   
		selectEmulator_launch_apiDemosApp(); 
		/* NOTE: In practice, App will always be launched on the Home page first then directly to some specific page without in-between
		   navigations using appPackage & appActivity  */
		
		//Below:   Arguments:==>  String appPackage, String appActivity
		//So now, app will launch on some specific page of the activity level of=  io.appium.android.apis.preference.PreferenceDependencies
		 //Activity activity= new Activity("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");    
		//driver.startActivity(activity);  // Note: it is deprecated (was supposed to launch the app on some specific page.
		
		//So on Appium official webpage [[https://github.com/appium/java-client]], consult the "CHANGELOG.md" and go from there.
		// where it tells you: """ Removed startActivity method from AndroidDriver. Use 'mobile: startActivity' extension method instead""""
		// This reminds me of the JavaScript code for mobile gestures...
		
		//Code sample was found here=====>    https://github.com/appium/appium-uiautomator2-driver#mobile-startactivity
		// where "intent" is appPackage/appActivity
		
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
			    "intent", "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"
			)); 
		Thread.sleep(3000L); // for viewing slow execution.
		
	}  
	
       
		

}
