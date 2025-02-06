package AppiumExamples.Appium_Hardcoded_Examples;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;

public class K_assignment extends Base {          // UNDER CONSTREUCTION
	//App >
	
	public void appToAlertDialogs()  // wrapping the 2 lines of code inside this method as they're going to be used across all methods/tes cases in this .java class file.
	{   
		//click on 'App'
		driver.findElement(AppiumBy.accessibilityId("App")).click();
		//click on 'Alert Dialogue'
		driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();
	}
	
	
	@Test
	public void test1() throws MalformedURLException, URISyntaxException, InterruptedException
	{
		selectEmulator_launch_apiDemosApp(); 
		appToAlertDialogs();
		//Click on the 2nd item (Ok Cancel dialogue with a long message)
	
		
		
		
		Thread.sleep(3000L); // to view actions before abruptly quitting the app.	
	}
	
	
	
	
	@Test (enabled=false)
	public void test2()
	{
		
	}
	
	
	
	
	@Test(enabled=false)
	public void test3()
	{
		
	}

}
