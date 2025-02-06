package AppiumExamples.Appium_Hardcoded_Examples;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;



// Download the real-time eCommerce hybrid app (General-Store.apk):  https://rahulshettyacademy.com/practice-project.
// Place it under the 'resources' folder.
// In the 'Base'.java class file, write it's launching method.
// In Appium Inspector, configure the capability set & save it:
	//: One needs to connect Appium Inspector to the specific device and the specific app within that specific device that we're going to automate.
public class N_realTime_eCommerce_app extends Base {
	/*
	 * TC#1:
	 * 1- Launch the app.
	 * 2- Click on the country drop down in order to open it.
	 * 3- Select a country 'Canada'
	 * 4- Fill out the field of "Your Name"
	 * 5- Select 'Female' Gender
	 * 6- Click on "Let's Shop" button.
	 * 7- Ensure user is navigated to the "products" page.
	 */
	
	@Test
	public void fillInfo_clickLetsShop() throws MalformedURLException, URISyntaxException, InterruptedException
	{
		String country= "Albania";
		String name= "Some random name !";
		String defaultText= "Enter name here";
		//1:
		selectEmulator_launch_generalStoreApp(); // Launching the eCommerce hybrid app.
		//2: 
		Thread.sleep(3000L); // to be replace by explicit wait.
		// First ensure the drop down is NOT open by default ( where TagName 'android.widget.ListView"' is not yet generated): Note: TagName isn't a valid Appium selector.
		try {
		driver.findElement(By.xpath("//android.widget.ListView")); 
		Assert.assertTrue(false, "Bug: Dropdown is already open before user clicks on it");}
		catch(NoSuchElementException e)   // always specify the type of exception to catch instead of 'Exception e', so that one knows in case the 'try'line throws a different unexpected exception !
		{
			Assert.assertTrue(true);
			System.out.println("Dropdown is closed by default before user clicks on it");	} // to be replaced by a log4j log.
		
		// Clicking on the drop down to open it:
		driver.findElement(By.cssSelector("[id*='spinnerCountry']")).click(); 
		
		// Validating drop down is open (When it is, TagName of 'android.widget.ListView' is generated):
		Thread.sleep(4000L); // as it takes time for the dropdown to open up & load all elements inside it.
		try {
			driver.findElement(By.xpath("//android.widget.ListView"));
			System.out.println("DropDown is open after the user clicked on it."); }
		catch(NoSuchElementException e) // always specify the type of exception to catch instead of 'Exception e', so that one knows in case the 'try'line throws a different unexpected exception !
		{
			Assert.assertTrue(false, "Bug: Drop down is still closed even though user clicked on it");
		}
		
		//3: Utilizing the preferred shortcut for scrolling with AndroidUiAutomator (To scroll down to & select 'Canada')
		// CRUCIAL: Even if the country to be selected is already visible on the page where no scrolling is needed in order to select it, then
		 // still- use the scrolling code, as otherwise (Such as using try catch statement, where 'catch' contains the scrolling action) renders unoptimized
		 // code. Indeed, "scrolling to an already visible element is often done to ensure focus, trigger JavaScript events, or enhance visibility for debugging purposes."
		
		scrollWithAndroidUiAutomator(country); //scrolls to the country wanted. Now to click on it:
		
		String countryLocator= "[text= '" +country+ "']";
		driver.findElement(By.cssSelector(countryLocator)).click();
		
		/* Also for clicking, the unoptimized code looks like:
		List<WebElement> elements= driver.findElements(By.id("android:id/text1"));
		for(int i=0; i<elements.size(); i++)
		{
			if(elements.get(i).getDomAttribute("text").equalsIgnoreCase(country))
					{
				      elements.get(i).click();
				      break;
					}
			else if(i==elements.size()-1) // will only be executed if the wanted items is NOT in the list.
			{
				Assert.assertTrue(false, "Country of: " + country + " is NOT in the list to start with"); 
			}
		}   */
		
		// Ensuring that 'Canada' is selected:
		Thread.sleep(2000L); // as it takes time, after clicking on the country, for the drop down to close and show the country
		Assert.assertEquals(driver.findElement(By.id("android:id/text1")).getDomAttribute("text"), country, "User selected the country of: " + country + 
				" .However, UI is showing a different one");
		// The assertion above can also take this argument instead: driver.findElement(By.id("android:id/text1")).getText()); // return the country too.
	
		//4:
		//Ensuring name field initially containing the default text of "Enter name here"
		WebElement fieldElement= driver.findElement(By.cssSelector("[id*= 'nameField']"));
		Assert.assertEquals(fieldElement.getDomAttribute("text"), defaultText, "text field does NOT have the default intial text of: "+ defaultText);
		//Entering a text
		fieldElement.sendKeys(name);
		//Validating that the correct text was entered
		Assert.assertEquals(fieldElement.getText(), name,"Field has a text different from what user entered. " + "User entered: " +name );
		
		//5:
		//Ensure gender of "Male' is already initially selected by default:
		WebElement maleRadio= driver.findElement(By.cssSelector("[id*= 'radioMale']"));
		Assert.assertEquals(maleRadio.getDomAttribute("checked"), "true", "Radio button of 'Male' is NOT initially selected by default [Goes against requirement]");
		// .isSelected() instead of .getDomSAttribute() where Assert.assertTrue/AssertFalse is to be utilized. The Q is, how reliable is .isSelected() method ??
		
		//Select the 'female' radio button
		WebElement femaleRadio= driver.findElement(By.cssSelector("[id*= 'radioFemale']"));
		femaleRadio.click();
		//Validate that the 'female' radio button has indeed been selected (Shown so in the UI)
		Assert.assertEquals(femaleRadio.getDomAttribute("checked"), "true", "Radio button of 'Female' can NOT be selected");
		
		//Select the 'male' radio button (Switching back from 'female radio button').
		maleRadio.click();
		Assert.assertEquals(maleRadio.getDomAttribute("checked"), "true", "Radio button of 'Female' can NOT be selected");
		
		//Validate that the 'male' radio button has indeed been selected (Shown so in the UI)
		Assert.assertEquals(maleRadio.getDomAttribute("checked"), "true", "Radio button of 'male' can NOT be selected after 'female' has indeed been selected");
		
	    //6:
		driver.findElement(By.cssSelector("[id*= 'btnLetsShop']")).click();
		//Validating button has been clicked, by ensuring the app navigates to the "Products" page
		// In real-time, an explicit wait is indispensable. In here, explicit wait to ensure that the product page is navigated to.
		Thread.sleep(2000L);  //debugging
		// to be continued (Add an Explicit wait to verify that the app navigates to the product page as a result of clicking on
		 // the button ion step 6
		
	}  
}
	  
