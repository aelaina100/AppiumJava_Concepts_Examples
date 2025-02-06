package AppiumExamples.Appium_Hardcoded_Examples;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;

public class D_navigationTest extends  Base {
	
	
	//Below a method/test case to click on the "Graphics" button.
	@Test(enabled=false)
	public void navigateToGraphicsButton() throws MalformedURLException, URISyntaxException
	{
		selectEmulator_launch_apiDemosApp();
		driver.findElement(AppiumBy.accessibilityId("Graphics")).click(); 
		
		//Last line above: No need for: this.driver= driver; 
			//Since 'driver' is contained in selectEmulator_launch_apiDemosApp();  
			// .However; if this last line is the 1st one in this method/ test case, then this.driver=driver is needed 
	}
	 
	
	
	//Below a method/test case to click on the "Preference" button.
	@Test
	public void navigateToPreferenceButton( ) throws MalformedURLException, URISyntaxException 
	{
		selectEmulator_launch_apiDemosApp();
		driver.findElement(AppiumBy.accessibilityId("Preference")).click(); 
	}
	
	
	 
	
	
	

	 
}
