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

public class J_DragNdrop extends Base{
	
	/* Views > Drag and Drop.
	 * The UI behavior is that:===>
	 * 
	 * A- Before performing this action:          The box element with the [id = io.appium.android.apis:id/drag_result_text]   has a text of Null (White space)
	 * B- After successful Drop and Drag action:  It has text attribute of "Dropped!"
	 * C- After a failing Drop and Drag action:   It has text attribute of "No drop"    
	 */
	@Test(enabled= false)
	public void dragDropGestureHardCoded() throws MalformedURLException, URISyntaxException, InterruptedException
	{
		selectEmulator_launch_apiDemosApp();
		// Click on 'Views'
	    driver.findElement(AppiumBy.accessibilityId("Views")).click();
	    // Click on 'Drag an d Drop'
	    driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
	    
	    WebElement dot1= driver.findElement(By.cssSelector("[id*= 'drag_dot_1']"));  // Utilizing regular expressions for CSS Selectors.
	    //WebElement dot2= driver.findElement(By.cssSelector("[id*= 'drag_dot_2']"));
	    //WebElement dot3= driver.findElement(By.cssSelector("[id*= 'drag_dot_3']"));
	    
	   // Before dragging & dropping, ensure that no text is present in the 'box element' (The correct UI behavior)
	    WebElement boxElement= driver.findElement(By.cssSelector("[id*= 'drag_result_text']"));
	    Assert.assertEquals(boxElement.getDomAttribute("text"), "", "Exception: As text IS displayed.."); //Note: "" does NOT equal " " as space is a character in & by itself.
	    		
	    
	    /*  // Code below for debugging purposes:
	        System.out.println("******########*******");
	    	System.out.println(driver.findElement(By.cssSelector("[id*= 'drag_result_text']")).getDomAttribute("text"));
	    	System.out.println("******########*******"); 
	    */
	    
	    
	    ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
	        "elementId", ((RemoteWebElement) dot1).getId(),
	        "endX", 669,   // Values come from the Appium Inspector utilizing the option of "Tab/Swipe by coordinates" [Center coordinates of target where element is dropped].
	        "endY", 1011
	    ));  // Try  x669 y1011...x644 y584
	    
	    //Now, ensure that the dragging & dropping has been successfully implemented
	    Assert.assertEquals(boxElement.getDomAttribute("text").toLowerCase().trim(), "DroPpEd!".toLowerCase().trim(), "Wrong message is displayed !..");
	    // CRUCIAL NOTE: the use of the 'boxElement' of WebElement return type again in here will NOT throw an element stale exception as this element 
	    // never gets modified in any shape or form as a result of storing it first and then performing other actions on the same web page.
	    
	    Thread.sleep(4000L); // debugging purposes: to see the entire process before abruptly quitting the app.
	}
	
	
	@Test
	public void dragDropGestureUsingUtility() throws MalformedURLException, URISyntaxException, InterruptedException
	{
		selectEmulator_launch_apiDemosApp();
		// Click on 'Views'
	    driver.findElement(AppiumBy.accessibilityId("Views")).click();
	    // Click on 'Drag an d Drop'
	    driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
	    
	    WebElement dot1= driver.findElement(By.cssSelector("[id*= 'drag_dot_1']"));  // Utilizing regular expressions for CSS Selectors.
	   // WebElement dot2= driver.findElement(By.cssSelector("[id*= 'drag_dot_2']"));
	    //WebElement dot3= driver.findElement(By.cssSelector("[id*= 'drag_dot_3']"));
	    
	    // Before dragging & dropping, ensure that no text is present in the 'box element' (The correct UI behavior)
	    WebElement boxElement= driver.findElement(By.cssSelector("[id*= 'drag_result_text']"));
	    // Below, I will simply use .getText() method instead of the .getAttribute() one used in the hard coded method/test case above.
	    Assert.assertEquals(boxElement.getText(), "", "Exception: As text IS displayed.."); //Note: "" does NOT equal " " as space is a character in & by itself.
	    
	    dragAndDrop(dot1,644,584);   //// Try  x669 y1011...x644 y584
	    
	  //Now, ensure that the dragging & dropping has been successfully implemented
	    Assert.assertEquals(boxElement.getText().toLowerCase().trim(), "DroPpEd!".toLowerCase().trim(), "Wrong message is displayed !..");
	    Thread.sleep(4000L); 
	}

}
