package AppiumExamples.Appium_Hardcoded_Examples;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;

public class I_Swiping extends  Base {
	
	//Views > Gallery > 1.Photos > Now: Apply 1 swipe to the left side
	
	/*                  The mobile gesture of Swiping  [Done via The JavaScriptExecutor class]
	 * 
	 *  https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/android-mobile-gestures.md (As of now)
     *   or simply google "Appium github gestures".
	 */
	
	@Test(enabled=false)
	public void swipingHarcoded() throws MalformedURLException, URISyntaxException, InterruptedException
	{
		selectEmulator_launch_apiDemosApp();
		// Click on 'Views'
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		// Click on 'Gallery'
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		// Click on '1.Photos'
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click(); // use a css regular expression instead.
		Thread.sleep(2000L);
		
	   /* Crucial: Now, there exists scenarios where swiping code doesn't simulate the user interaction (no swiping is performed). WHERE,
	    * No exception is thrown !. Consequently, the line(s) of code below that of swiping renders an error/exception !
	    * Such scenarios contribute to flakinness. This is one scenario where Junior/mid-level automation engineers struggle to understand the
	    * root cause of the issue. 
	    * 
	    * For resolution:====> 
	    * The 1st image has an attribute of [focusable ='true']. it's why it's swipeable.
	    * When the value is 'false', the image isn't swipeable. Nevertheless the swiping code will not, sometimes, throw an error.
	    * The solution is: Immediately above the swiping code, write an assertion ensuring that [focusable ='true'].
	    * ALSO, immediately below the swiping code, write an assertion ensuring that now [focusable ='false'] (As this is the current UI behavior).
	    * [[Because, sometimes, even if [focusable ='true'] scrolling will not be simulated on the UI. Hence, 'focusable' will NOT become "false".
	    *  implementation of such knowledge is what distinguishes Senior testers from the novice or intermediate ones.
	    */ 
		// DO not to include explicit waits (Will be added when this project is complete).
		WebElement element= driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
		Assert.assertEquals(element.getDomAttribute("focusable"),"true");
		//System.out.println(element.getDomAttribute("focusable"));
		
		// Crucial: the above TagName 'android.widget.ImageView' also happens to be a className. If it's considered the later, then the below line is utilized instead:
		//WebElement element= driver.findElements(By.className("android.widget.ImageView")).get(0);
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId",  ((RemoteWebElement) element).getId(),     //((RemoteWebElement) element).getId()
		        "direction", "left",
		        "percent", 0.1   // This is the size of the scroll.   0.5 will swipe to, approximately, the middle of the images.
		));
		
		Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getDomAttribute("focusable"), "false"); // if it passes, then the gesture of swiping HAS BEEN INDEED SUCCESSFULLY simulated in the UI !.
		// above line:     element.getDomAttribute("focusable")   is going to throw an element stale exception.
		
		Thread.sleep(2000L); // debugging purposes only.
}
	
	@Test  // using the swiping utility instead of hard coding
	public void swipingUsingBaseUtility() throws MalformedURLException, URISyntaxException, InterruptedException
	{
		selectEmulator_launch_apiDemosApp();
		// Click on 'Views'
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		// Click on 'Gallery'
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		// Click on '1.Photos'
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click(); // use a css regular expression instead.
		Thread.sleep(2000L);
		
		WebElement element= driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
		Assert.assertEquals(element.getDomAttribute("focusable"),"true");
		
		swiping(element, "left", 0.1);
		
		Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getDomAttribute("focusable"), "false"); // if it passes, then the gesture of swiping HAS BEEN INDEED SUCCESSFULLY simulated in the UI !.
		// above line:     element.getDomAttribute("focusable")   is going to throw an element stale exception.
		Thread.sleep(2000L); // debugging purposes only.
}
	}
  
