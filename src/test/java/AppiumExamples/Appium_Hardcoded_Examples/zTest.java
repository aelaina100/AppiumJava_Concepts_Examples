package AppiumExamples.Appium_Hardcoded_Examples;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class zTest extends Base {
	
	@Test // previos program with no comments
	public void test() throws MalformedURLException, URISyntaxException, InterruptedException
	{   
		//1
		selectEmulator_launch_generalStoreApp();
		
		//2
		Thread.sleep(3000L);
		driver.findElement(By.cssSelector("[id*= 'nameField']")).sendKeys("Automation text");
		driver.findElement(By.cssSelector("[id*= 'btnLetsShop']")).click();
	

		Thread.sleep(3000L);
		int count=0;
		String wantedProducts[]= {"Air Jordan 1 Mid SE", "Nike SFB Jungle"};  //"PG 3"
		//"Nike SFB Jungle"   if this last item is the wanted product, then it won't be added to cart because its "Added to Cart" button won't be shown
		 // NoSuchElementException
		
		for(int i=0; i<wantedProducts.length; i++) // i=0, i=1, i=2
		{
			
			scrollWithAndroidUiAutomator(wantedProducts[i]); // even if the item is already present in the UI, scrolling action provides a focus on it (Never hurts !).
			
		    // Locate all product name elements
			List<WebElement>elements= driver.findElements(By.cssSelector("[id*='productName']"));
		    // Iterate through the product name elements
			for(int j=0; j<elements.size(); j++)
			{
				  // Check if the product matches the desired product
				if(elements.get(j).getDomAttribute("text").equalsIgnoreCase(wantedProducts[i]))
						{
					
					     
					      try {
					       // Click the corresponding 'Add to Cart' button
					      List<WebElement> addToCartButtons= driver.findElements(By.cssSelector("[id*='productAddCart']"));
					      addToCartButtons.get(j).click();
					      //One could get NoSuchElementException because even though index j could point to the correct "AddToCart" button of the wanted product
					       // where this button is still NOT visible in the UI (Where only the product's name is visible). 
					         // In this specific example, this happens with the product on bottom that is, as of now, "Nike SFB Jungle".
					       //
					   
					      
					         } // try bracket
					      catch(Exception e) { //NoSuchElementException
					    
					    	  System.out.println("^***&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&Debugging: I am in the [Catch] block");
					    	 
					      }
						}
				
				
			}
		}
	
	
		
		//System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"); //debugging purposes
		/*
		 * 
		 *           driver.findElementByAndroidUIAutomator(
		            "new UiScrollable(new UiSelector().scrollable(true))" +
		            ".scrollIntoView(new UiSelector().xpath(\"" + xpath + "\"))");
		 */
			
         
	}

	

}
