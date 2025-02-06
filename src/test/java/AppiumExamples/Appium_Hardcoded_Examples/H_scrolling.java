package AppiumExamples.Appium_Hardcoded_Examples;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;

public class H_scrolling extends Base{
	
	boolean canScrollMore;
	/*
	 * The mobile gesture of scrolling.
	 *  Assume you want to click on "WebView" for which, user needs to scroll down to first.
	 *  
		So, there are 2 ways of doing so:
		
	 *  A- [Not preferred]:===>
	 *  
	 *  The JavaScriptExecutor [As shown in the previous example with the below link:===>
	 *  https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/android-mobile-gestures.md (As of now)
        or simply google "Appium github gestures". */
        
        
        
        /*
        B- The preferred shortcut of:
        
           Using the locator of "androidUIAutomator" ========>    driver.findElement(AppiumBy.androidUIAutomator(""));
           where its argument contains the script for scrolling:
           
           So, Inside the argument:
           Create an object of the class 'UiScrollable' & inside it give the text of the element one wants to scroll to ==> the format of this is:
           
           new UiScrollable(new UiSelector()).scrollIntoView(text(\"INSERT TEXT HERE\"));
           Where" scrollIntoView (= scrollIntoView the text of the element)
           ====================================================================
           The find line is:
           driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"))
           // where it also identifies the element of 'WebView'. Hence, one could also add .click() to the above line
            * 
            */
   
	@Test (enabled= false)
    public void scrollUsingAndroidUiAutomator() throws MalformedURLException, URISyntaxException, InterruptedException  {
    	
	selectEmulator_launch_apiDemosApp();
	driver.findElement(AppiumBy.accessibilityId("Views")).click();
	String text= "WebView";
	 scrollWithAndroidUiAutomator(text);
	//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));
	//Thread.sleep(2000L);   //To view things slowly before app closes.
	
    }
    
	
    /* The commented out code below is 100 percent correct. commented out as it's regarded as a utility that's now called from the base class.
	@Test(enabled=false)
	public void scrollWithAndroidUiAutomator() throws MalformedURLException, URISyntaxException, InterruptedException
	{   
		selectEmulator_launch_apiDemosApp();
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));
		Thread.sleep(2000L);   //To view things slowly before app closes.
		/*
		 * Side Note: Using the locator of .androidUIAutomator denotes the use of Google engine to identify the element.
		 *            In Google engine, in order to scroll, one has to create an object of the class 'UiScrollable'
		 *            and this class expects 'new UiSelector()' as an argument to determine the element we're scrolling into view.
		 *            
		 *            Inside this 'UiScrollable' class, there is the method of .scrollIntoView() where in it's
		 *            argument, pass the text of the element we want to scroll into view.
		 *            
		 *            So, scrolling won't cease until the text of 'WebView' is encountered.
		 *          
		 * 	
	} */

	@Test
	public void scrollUsingJavaScriptExecutor() throws MalformedURLException, URISyntaxException, InterruptedException
	{  
		selectEmulator_launch_apiDemosApp();
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		
		//boolean canScrollMore = false; // automatically initialized as 'false'.
		do
		{
		scrollWithJavaScriptExecutor();
		
		try
		{   // if the element is displayed in the list, click on it.
			if(driver.findElement(AppiumBy.accessibilityId("WebView")).isDisplayed())
			{
				driver.findElement(AppiumBy.accessibilityId("WebView")).click();
				break;
				
			}
			//.However; the behavior here is that, .isDisplayed() throws an exception
			//when the element is not displayed on the screen. it seems that the associated
			// <HTML code> will be generated in the DOM ONLY when the element is scrolled down to [Very exceptional behavior in here]
		}
		catch(Exception e)
		{
			continue;
		}
		
		}
		while(canScrollMore); // While more scrolling action can be performed.
		Thread.sleep(2000L); // to view a slow execution of the script (Debugging purposes) before app quits
		
		
		
		  //Below is the code without the use of utility:
		/*
		selectEmulator_launch_apiDemosApp();
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		
		boolean canScrollMore;
		do
		{
		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
		    "left", 100, "top", 100, "width", 200, "height", 200,
		    "direction", "down",
		    "percent", 2.0  //percent: The size of the scroll as a percentage of the scrolling area size
		)); 
		
		try
		{   // if the element is displayed in the list, click on it.
			if(driver.findElement(AppiumBy.accessibilityId("WebView")).isDisplayed())
			{
				driver.findElement(AppiumBy.accessibilityId("WebView")).click();
				break;
				
			}
			//.However; the behavior here is that, .isDisplayed() throws an exception
			//when the element is not displayed on the screen. it seems that the associated
			// <HTML code> will be generated in the DOM ONLY when the element is scrolled down to [Very exceptional behavior in here]
		}
		catch(Exception e)
		{
			continue;}
		}
		while(canScrollMore); // While more scrolling action can be performed.
		Thread.sleep(2000L); // to view a slow execution of the script (Debugging purposes) before app quits.
		*/
	     		
	 		
	}
	       }

