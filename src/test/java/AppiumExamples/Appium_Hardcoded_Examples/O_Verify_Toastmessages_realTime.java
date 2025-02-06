package AppiumExamples.Appium_Hardcoded_Examples;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class O_Verify_Toastmessages_realTime extends Base {
	/*
	 * TC#: 
	 * 1- Don't fill out the field of "Your Name'.
	 * 2- Click on "Let's Shop" button.
	 * 3- Verify the toast message of "Please enter your name" appears.
	 * 
	 * 
	 */
	@Test
	public void toastmessages() throws MalformedURLException, URISyntaxException, InterruptedException
	{
		selectEmulator_launch_generalStoreApp();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		Thread.sleep(3000L);
		//Ensuring the toast msg does NOT appear before clicking on the button:
		
		// After inspecting the elements, it seems that the <HTML code>'s associated with the toast message, that are,:===>
		
		//  <android.widget.Toast text = "Please enter your name">
		//  <android.widget.Toast text = "Toast message"
		
		// will only be generated once the message actually indeed appears.
		// HOWEVER- These 2 HTML mark ups do NOT identify the UI toast message in the UI. In other words: 
		  // ,inspecting these HTMLs will NOT highlight the UI toast message in the UI which is indeed associated with it. THIS MEANS:
		   // the Explicit Wait (to wait for the toast msg to appear before throwing a timeout exception) should ONLY be with the
		    // method of "presenceOfElementLocated" and NEVER "visibilityOfElementLocated". because the latter would only work when
		     // the associated HTML mark up highlights the UI toast message (And this does not happen even though they're associated with each other).
				// utilizing "visibilityOfElementLocated" will throw a time out exception.
		try 
		{
	    driver.findElement(By.cssSelector("[text= 'Toast message']")); // 'Hopefully' throws NoSuchElementException = Toast msg not appearing in the UI. = its 
	    //associated HTML is can NOT be found/ Not generated.(According to the studied behavior of the UI when inspecting the elements).
	    // Including the other text attribute is wrong as the UI toast msg could change (As a result of a bug or requirement change).
	    // Including it only indicated a lack of login/ real-time experience of the QA.
	    
	    Assert.assertTrue(false, "BUG: Toast message appears before clicking on the button of: [Let's Shop]");
		}
		catch(NoSuchElementException e) // Always specify the exact type of exception to be caught.
		{
			Assert.assertTrue(true);
			System.out.println("PASS: Toast message does NOT appear [of: Please enter your name]");  
		}
		
		System.out.println("============================================================================================");
		
		//Clicking on the button.
		 driver.findElement(By.cssSelector("[id*= 'btnLetsShop']")).click();
	     
		 
		// verify the toast message appears + It's the correct one  
		 
		 /* Below: VERY IMPORTANT====>
		  * 
		  * In this very specific case, the instance CSS selector of : //android.widget.Toast:nth-child(1) which is a VALID selector (Verified) will NOT identify 
		  * the targeted element but
		  * its equivalent XPath will (In this very specific case).
		  * This is because:
		    the # of instance differed between that of xpath & that of CSS:
            this COULD be due to a hidden element that's not identified by the CSS, but is by the XPATH (Very important to know).
            Hence, the instance Xpath is utilized.
          */
          
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.Toast)[1]")));
		 
		 String expected_toastMsg= "Please enter your name".trim().toLowerCase();
		 try
		 {
		 Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getDomAttribute("text").trim().toLowerCase(), expected_toastMsg, "Expected & Actual toast messages are not the same !");
	     }
		 catch(NoSuchElementException  e) // In Try statement:  ""NoSuchElementException "" When No toast message appears in the UI.
		 {
			 Assert.assertTrue(false, "BUG: After clicking on 'Let's Shop' button, no toast message appears");
		 }

  } 
	
	//Below: Exact program above but commented out.
	@Test(enabled=false)
	public void commentedOut_toastmessage() throws MalformedURLException, URISyntaxException, InterruptedException
	{
		selectEmulator_launch_generalStoreApp();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		Thread.sleep(3000L);
		
		try 
		{
	    driver.findElement(By.cssSelector("[text= 'Toast message']"));  
	    Assert.assertTrue(false, "BUG: Toast message appears before clicking on the button of: [Let's Shop]");
		}
		catch(NoSuchElementException e) // Always specify the exact type of exception to be caught.
		{
			Assert.assertTrue(true);
			System.out.println("PASS: Toast message does NOT appear [of: Please enter your name]");  
		}
		
		//Clicking on the button.
		 driver.findElement(By.cssSelector("[id*= 'btnLetsShop']")).click();
	     
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.Toast)[1]")));
		 
		 String expected_toastMsg= "Please enter your name".trim().toLowerCase();
		 try
		 {
		 Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getDomAttribute("text").trim().toLowerCase(), expected_toastMsg, "Expected & Actual toast messages are not the same !");
	     }
		 catch(NoSuchElementException  e) // In Try statement:  ""NoSuchElementException "" When No toast message appears in the UI.
		 {
			 Assert.assertTrue(false, "BUG: After clicking on 'Let's Shop' button, no toast message appears");
		 }

    }
	
}
