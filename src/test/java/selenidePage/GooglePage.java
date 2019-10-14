package selenidePage;

import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.By;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Configuration;
import selenideConfig.ConfigKeys;

/**
 * Class representing Google main page.
 * 
 * @author Alan Buda
 */  
public class GooglePage {
	  
	  /**
	  * 
	  * Set automation driver.
	  * 
	  */ 
	  public static void setDriver() {
		Configuration.browser = ConfigKeys.getConfigKey("selenide_browser");
		Configuration.startMaximized = true;
	  }
	  
	  /**
	  * 
	  * Open Google main page.
	  *
	  * @return instance of GooglePage object. 
	  */
	  public GooglePage openHomepage() {
		Selenide.open(ConfigKeys.getConfigKey("home_page"));
		return page(GooglePage.class);		  
	  }
	  
	  /**
	  * Find result in Google.
	  * 
	  * @param text
	  *          text that should be typed into browser 
	  *      
	  * @return GooglePage object itself
	  * 
	  */  
	  public GooglePage searchFor(String text) {
	        $(By.name("q")).val(text).pressEnter();
	        return this;
	  }
	   
	  /**
	  * Return the search results.
	  * 
	  *      
	  * @return collection of results
	  * 
	  */
	  public ElementsCollection results() {
	        return $$(".srg .g");
	  }
}
