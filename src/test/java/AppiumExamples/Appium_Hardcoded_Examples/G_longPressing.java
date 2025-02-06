package AppiumExamples.Appium_Hardcoded_Examples;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;

/*                      ************************************** READ ME FIRST ***************************************
 *          Mobile gestures are: Long Pressing, Scrolling, Drag & Drop, Swiping, & Pinching.
 *          
 * Appium library developers have provided excellent documentation with code samples for automating mobile gestures for Android.
 * 
 * It can be found on Appium official website: (Also, web page is saved under this project/ where the pom.xml file is)
 * https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/android-mobile-gestures.md (As of now) or simply google "Appium github gestures"
 * 
 * For one of the gestures, of Long pressing, the following sample code is provided:
 * 
 * 
         ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
              "elementId", ((RemoteWebElement) element).getId()
         ));
 * 
 *  So, according to this sample code, the JavaScript code of:
 * 
 *   "mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000) 
 *    has to be injected into our Appium code using the .executeScript() method.
 * 
 * 
 * . Also, this JavaScript code has 2 parameters:
 * 
 *   1st Parameter:    "mobile: longClickGesture"     [Is called an event (The event of long clicking in this case) ]
 *   
 *   2nd Parameter:     ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()
 *                      
 *                      is a map of Key:Value
 *                      
 *                      key:   "elementId"
 *                      Value: ((RemoteWebElement) element).getId()   [On which element we're going to apply the long click,
 *                                                                     where element is identified by either a locator (Any type) or by a coordinate.
 *                                                                     
 *                                                                     
 *   3rd parameter:  (Better to add a 2nd map of Key:Value for how long to press)
 *   
 *                    Key:  "duration"
 *                    Value: in milliseconds 
 * 
 * 
 * Separate Note: resource-id attribute is the same as id.
 */ 
public class G_longPressing extends Base {
	
	@Test
	public void longPressGesture() throws MalformedURLException, URISyntaxException
	{
		selectEmulator_launch_apiDemosApp();
		// 1- Click on 'views':
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		//2- Click on 'Expandable Lists':
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		//3- Click on 'Customer Adapter':
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		//4- Long press on 'People Names': [As a result, a pop-up appears].
		
		/* The below code is for long pressing. However; It's regarded as a utility. Hence, moved to the base class.Later, may be moved under a utility folder.
	    WebElement element= driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"People Names\")"));
	    		
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
		    "elementId", ((RemoteWebElement) element).getId(), "duration", 2000  
		));  // long press for 2 seconds.                                         */
		
		WebElement element= driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"People Names\")"));
		longPressing(element);
		//5- Ensure, "Sample menu" is displayed in the drop down.[commented out:BAD coding as "Sample menu" MUST be stored in an array.Not a string]
		/*
		ActualArrayList<String> ActualArrayList= new ActualArrayList<String>();
		String expectedText= "Sample menu".toLowerCase().trim();
		
		List<WebElement> elements= driver.findElements(By.xpath("//android.widget.TextView[@resource-id='android:id/title'] "));
		
		for(int i=0; i<elements.size(); i++)
		{
			String actualText= elements.get(i).getText().toLowerCase().trim();
			ActualArrayList.add(actualText);
		}
		
		Assert.assertTrue(ActualArrayList.contains(expectedText), "EXCEPTION: Drop down does NOT contain the text of: '" + expectedText + "' . It instead contains "
				+ "the texts of: " +ActualArrayList);
		//without the custom message, the exception will be:    ""expected [true] but found [false]""
		
		*/
		
		/* Or use: 
		Assert.assertListContainsObject(ActualArrayList, expectedText, "Add the exact same msg in the above assertion");
		*/
		
		//6- Ensure that "Sample action" is present in the drop down list.
		String expectedItems[]= {"sample menU" , "SAMple actIon" }; // Convert later only as soon as needed to an ArrayList (Even if inside a for-loop). This is due to memory management
		                                           // Even if there is only 1 item, never declare a string (For maintenance if future item(s) is/ are added).
		
		// Now, the logic is to get the text of ALL box elements present in the drop down, and storing them into an ActualArrayList so that
		// we could use the .contains()/.containsAll() method
		ArrayList<String> ActualArrayList= new ArrayList<String>();
		List<WebElement> elements= driver.findElements(By.id("android:id/title"));
		for(int i=0; i<elements.size(); i++)
		{
			String dropDownText= elements.get(i).getText().toLowerCase().trim(); //to negate case sensitivity & any added space(s) before/after each string.
			ActualArrayList.add(dropDownText);
		}
		
		// Now, in order to use the .contains()/.containsAll() methods, the array (fixed-length data structure) declared at the very beginning
		 // must be converted into an ActualArrayList.==>
		List<String> expectedItems_ArrayList= Arrays.asList(expectedItems);
		
		// Plus in order to negate case sensitivity and any added space(s) before/after each string do the following: 
		 List<String> finalexpectedItems_ArrayList =expectedItems_ArrayList.stream().map(s->s.toLowerCase().trim()).collect(Collectors.toList());
		 
		 Assert.assertTrue(ActualArrayList.containsAll(finalexpectedItems_ArrayList), "The displayed list of: " + ActualArrayList + " does NOT contain: "+ finalexpectedItems_ArrayList);
			
		//7- Ensure that "Sample menu" & "Sample action" are present in the drop down list.
		  // It is the previous lines of code PLUS Simply include them both to the above declared array (The fixed length data structure).
		
		//8- Ensure that the UI drop down, only contains "Sample menu" & "Sample action" IN ORDER.
		  // Simply include them both to the above declared array (The fixed length data structure) &
		 // use Assert.assertEquals
		 Assert.assertEquals(ActualArrayList, finalexpectedItems_ArrayList, "The actual displayed list of: " + ActualArrayList + " does NOT equal/[ NOT in order] the expected on of: " + finalexpectedItems_ArrayList );
	
		//9- Ensure that the UI drop down, only contains "Sample menu" & "Sample action" IN NO PARTICULAR ORDER.
		  // Simply include them both to the above declared array (The fixed length data structure) &
		 // use Assert.assertEqualsNoOrder
		 Assert.assertEqualsNoOrder(ActualArrayList, finalexpectedItems_ArrayList, "The actual displayed list of: " + ActualArrayList + " does NOT equal the expected on of: " + finalexpectedItems_ArrayList);
		
		
		
		/****************************  TO DO LATER *************************
		 *  // Ensure that there are 2 elements with the exact same texts. (Plus, at least 2, more than 2, etc.)
		//Checkout the below methods + the related unlisted others:===>
        //Assert.assertListContains(null, null, expectedText)   
        //Assert.assertListNotContains(null, null, expectedText)
		 * 
		 * 
		 */
		
		// Now this method/test case can be improved as illustrated in the below method/test case:==>
	}

}
