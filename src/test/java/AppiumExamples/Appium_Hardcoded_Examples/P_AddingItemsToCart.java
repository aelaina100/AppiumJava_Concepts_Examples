package AppiumExamples.Appium_Hardcoded_Examples;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class P_AddingItemsToCart extends Base{
	
	/* TC #1:
	 * 1-  Launch app.
	 * 2- fill out name & click on 'Let's Shop' button.
	 * 3- Add the following items to cart:   "Air Jordan 1 Mid SE"  & "Nike SFB Jungle", WHERE:
	 *    The button, of each item, changes text from 'ADD TO CART" to "ADDED TO CART" immediately after being clicked on.
	 *    
	 *    TC #2:
	 *    1- Add two items to cart
	 *    2- Validate that cart contains the exact 2 items.
	 */
	
	@Test
	public void test() throws MalformedURLException, URISyntaxException, InterruptedException
	{   
		//1
		selectEmulator_launch_generalStoreApp();
		
		//2
		Thread.sleep(3000L);
		driver.findElement(By.cssSelector("[id*= 'nameField']")).sendKeys("Automation text");
		driver.findElement(By.cssSelector("[id*= 'btnLetsShop']")).click();
	
		
		//3 (Below code is simply a bad practice because..for the 2nd item, one has to replicate these lines of code.. let alone a 3rd, 4th,..etc. items,
		 // and still, even if it's only for 1 item (What if, in the future you want to add more than 1 item ?...you see this is some UNOPTMIZED code)
		
		/*
		Thread.sleep(3000L);
		scrollWithAndroidUiAutomator("Air Jordan 1 Mid SE"); // even if the item is already present in the UI, scrolling action provides a focus on it (Never hurts !).
		
		List<WebElement>elements= driver.findElements(By.cssSelector("[id*='productName']"));
		for(int i=0; i<elements.size(); i++)
		{
			if(elements.get(i).getDomAttribute("text").equalsIgnoreCase("Air Jordan 1 Mid SE"))
					{
				      driver.findElements(By.cssSelector("[id*='productAddCart']")).get(i).click();
					}
		}
		
		*/
		 
		// An optimized code, would be to add the wanted items in an array and then covert it to an arraylist ONLY as soon as needed (Due to memory management as opposed to
		// directly creating an arrayList) where we're looping through the arraylist
		Thread.sleep(3000L);
		int count=0;
		String wantedProducts[]= {"Air Jordan 1 Mid SE", "PG 3" }; // even if this array contains 1 item only. So that in future, more items could be added.
		//"Nike SFB Jungle"   if this last item is the wanted product, then it won't be added to cart because its "Added to Cart" button won't be shown. Hence
		 // NoSuchElementException  will be thrown. In order to fix this issue:  A try/catch statement is needed (Always include such a possibility even if this
		  // would never happen.
		//outerLoop: // Labeled loop for breaking out of nested loops
		for(int i=0; i<wantedProducts.length; i++) // i=0, i=1
		{
			// Scroll to ensure the product is in focus
			scrollWithAndroidUiAutomator(wantedProducts[i]); // even if the item is already present in the UI, scrolling action provides a focus on it (Never hurts !).
			
		    // Locate all product name elements
			List<WebElement>elements= driver.findElements(By.cssSelector("[id*='productName']"));
		    // Iterate through the product name elements
			for(int j=0; j<elements.size(); j++)
			{
				  // Check if the product matches the desired product
				if(elements.get(j).getDomAttribute("text").equalsIgnoreCase(wantedProducts[i]))
						{
					
					      count++;
					      try {
					       // Click the corresponding 'Add to Cart' button
					      driver.findElements(By.cssSelector("[id*='productAddCart']")).get(j).click(); 
					      //Above- sometimes (ex: last product scrolled down to), it's 'Add to Cart' button isn't visible on the screen, but only the product's image
					      //consequently, an exception is thrown "ElementNotFoundException". Hence, the 'try/catch' statement.
					      
			              // Exit the loop as soon as all wanted products are added:
					      if(count==wantedProducts.length)
					      {
					    	  break; //outerLoop;
					      }
					         } // try bracket
					      catch(NoSuchElementException e) {
					    	  //catch this exception as a result of the product being partially scrolled down to (product's image is shown, but its 'Add to Cart' button isn't)
					    	  // so, scroll down so that both, the image that's already visible along with its button are now BOTH visible.
					    	  System.out.println("^***&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&Debugging: I am in the [Catch] block");
					    	 // scrollWithAndroidUiAutomator("ADD TO CART"); 
					    	  // Above line: will NOT scroll down to this text if the same text is already visible in the UI= serving to focus on it.
					    	  
					    	  // Plan: find an xpath/css for the buttom of "Add to Cart" and use the below formula:
					    	    // //android.widget.TextView[@text='']/following-sibling::android.widget.LinearLayout[2]/android.widget.TextView[2]
				    	  
					      }
						}
				
				
			}
		}
	
		//Now, add to cart the products that contain the keywords of "Jordan" & "Nike".
		 //Answer: same lines of code above but use:  if(elements.get(j).getDomAttribute("text").contains(wantedProducts[i]));
		// instead of:                                if(elements.get(j).getDomAttribute("text").equalsIgnoreCase(wantedProducts[i]))
		// again remember that,  .contains() is a method present in the classes of "String" & arrayList.
		
		/*
		 *                  IN CONCLUSION: Items to be initially added to an array whether this array is going to be converted to an arraylist or not (Best practices).
		 *                                 It is converted to an arrayList, so that the .contains() method of the arrayList is to be applied.
		 *                                 In the case that the initial array contains one item only, where it's converted, afterwards, to an arrayList, the use
		 *                                 of .contains() is always better than .equalsIgnoreCase()
		 * 
		 */
		
		//System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"); //debugging purposes
		/*
		 *           driver.findElementByAndroidUIAutomator(
		            "new UiScrollable(new UiSelector().scrollable(true))" +
		            ".scrollIntoView(new UiSelector().xpath(\"" + xpath + "\"))");
		 */
			
         
	}
	
	@Test(enabled=false)
	public void withoutTryCatch() throws InterruptedException, MalformedURLException, URISyntaxException
	{
		   
			//1
			selectEmulator_launch_generalStoreApp();
			
			//2
			Thread.sleep(3000L);
			driver.findElement(By.cssSelector("[id*= 'nameField']")).sendKeys("Automation text");
			driver.findElement(By.cssSelector("[id*= 'btnLetsShop']")).click();
		
			
			//3 (Below code is simply a bad practice because..for the 2nd item, one has to replicate these lines of code.. let alone a 3rd, 4th,..etc. items,
			 // and still, even if it's only for 1 item (What if, in the future you want to add more than 1 item ?...you see this is some UNOPTMIZED code)
			
			/*
			Thread.sleep(3000L);
			scrollWithAndroidUiAutomator("Air Jordan 1 Mid SE"); // even if the item is already present in the UI, scrolling action provides a focus on it (Never hurts !).
			
			List<WebElement>elements= driver.findElements(By.cssSelector("[id*='productName']"));
			for(int i=0; i<elements.size(); i++)
			{
				if(elements.get(i).getDomAttribute("text").equalsIgnoreCase("Air Jordan 1 Mid SE"))
						{
					      driver.findElements(By.cssSelector("[id*='productAddCart']")).get(i).click();
						}
			}
			
			*/
			 
			// An optimized code, would be to add the wanted items in an array and then covert it to an arraylist ONLY as soon as needed (Due to memory management as opposed to
			// directly creating an arrayList) where we're looping through the arraylist
			Thread.sleep(3000L);
			int count=0;
			String wantedProducts[]= {"Air Jordan 1 Mid SE", "Nike SFB Jungle"}; // even if this array contains 1 item only. So that in future, more items could be added.
			//"Nike SFB Jungle"   if this last item is the wanted product, then it won't be added to cart because its "Added to Cart" button won't be shown. Hence
			 // NoSuchElementException  will be thrown. In order to fix this issue:  A try/catch statement is needed (Always include such a possibility even if this
			  // would never happen.
			outerLoop: // Labeled loop for breaking out of nested loops
			for(int i=0; i<wantedProducts.length; i++) // i=0, i=1
			{
				// Scroll to ensure the product is in focus
				scrollWithAndroidUiAutomator(wantedProducts[i]); // even if the item is already present in the UI, scrolling action provides a focus on it (Never hurts !).
				
				
			    // Locate all product name elements
				List<WebElement>elements= driver.findElements(By.cssSelector("[id*='productName']"));
			    // Iterate through the product name elements
				for(int j=0; j<elements.size(); j++)
				{
					  // Check if the product matches the desired product
					if(elements.get(j).getDomAttribute("text").equalsIgnoreCase(wantedProducts[i]))
							{
						
						      count++;
						       // Click the corresponding 'Add to Cart' button
						      driver.findElements(By.cssSelector("[id*='productAddCart']")).get(j).click(); 
						      //Above- sometimes (ex: last product scrolled down to), it's 'Add to Cart' button isn't visible on the screen, but only the product's image
						      //consequently, an exception is thrown "ElementNotFoundException". Hence, the 'try/catch' statement.
						      
				              // Exit the loop as soon as all wanted products are added:
						      if(count==wantedProducts.length)
						      {
						    	  break outerLoop;
						      }
							}
				}
			}
			Thread.sleep(5000L);
			//Now, add to cart the products that contain the keywords of "Jordan" & "Nike".
			 //Answer: same lines of code above but use:  if(elements.get(j).getDomAttribute("text").contains(wantedProducts[i]));
			// instead of:                                if(elements.get(j).getDomAttribute("text").equalsIgnoreCase(wantedProducts[i]))
			// again remember that,  .contains() is a method present in the classes of "String" & arrayList.
			
			/*
			 *                  IN CONCLUSION: Items to be initially added to an array whether this array is going to be converted to an arraylist or not (Best practices).
			 *                                 It is converted to an arrayList, so that the .contains() method of the arrayList is to be applied.
			 *                                 In the case that the initial array contains one item only, where it's converted, afterwards, to an arrayList, the use
			 *                                 of .contains() is always better than .equalsIgnoreCase()
			 * 
			 */
			
			//System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"); //debugging purposes
	        
	         
		}
	}


