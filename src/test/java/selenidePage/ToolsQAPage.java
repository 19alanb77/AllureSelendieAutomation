package selenidePage;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;
import java.io.File;
import org.openqa.selenium.By;
import selenideConfig.ConfigKeys;

/**
 * Class representing ToolsQA page.
 * 
 * @author Alan Buda
 */  
public class ToolsQAPage {
		
	   /**
	   * Insert name to the field.
	   *    
	   * @param name
	   *          value that should be inserted in name field 
	   * 
	   */
	   public void insertName(String name) {
	        $(By.name("firstname")).val(name);
	   }
	   
	   /**
	   * Insert surname to the field.
	   *    
	   * @param surname
	   *          value that should be inserted in name field 
	   * 
	   */
	   public void insertSurname(String surname) {
	        $(By.name("lastname")).val(surname);
	   }
	   
	   /**
	   * Pick sex and experience buttons.
	   *    
	   * @param sex
	   *          value that should be picked in sex field 
	   * 
	   * @param experience
	   *          value that should be picked in experience field 
	   * 
	   */
	   public void pickRadioButtons(String sex, String experience) {
		$("input").selectRadio(sex);
		$("input").selectRadio(experience);
	   }
	   
	   /**
	   * Choose date and upload file on the page.
	   *    
	   * @param date
	   *          value that should be inserted in date field 
	   * 
	   * @param tester
	   *          value that should be picked in profession field 
	   * 
	   */
	   public void chooseDateAndPicture(String date, String tester) {
		$(By.id("datepicker")).val(date);		   
		$$("input").find(value(tester)).click();
		File file = new File(ConfigKeys.getConfigKey("file_path"));
		$(By.id("photo")).uploadFile(file);
	   }
	   
	   /**
	   * Download file from the page.
	   *    
	   * @param tool
	   *          value that should be picked from available frameworks 
	   * 
	   */
	   public void downloadFramework(String tool) {
		$$("a").find(text("Test File to Download")).click();
		$$("input").find(value(tool)).click();
	   }
	   
	   /**
	   * Pick values from listboxes.
	   *    
	   * @param continent
	   *          value that should be picked in continent field
	   *
	   * @param command
	   *          value that should be picked in selenium commands field 
	   * 
	   */
	   public void pickFromListboxes(String continent,String command) {
		$(By.id("continents")).click();
		$$("option").find(text(continent)).click();
		$$("option").find(text(command)).click();
	   }
	   
	   /**
	   * Send completed form.
	   * 
	   */ 
	   public void sendForm() {
		$$(By.id("NextedText")).shouldHave(texts("Text1"));
		$(By.id("submit")).click();
	   }
	   
	   /**
	   * 
	   * Click on new page button.
	   *
	   * @return instance of ToolsQAPage object. 
	   */
	   public ToolsQAPage clickNewPage() {
		$(By.xpath("//div/form/fieldset/div[5]/a/strong")).click();
		return page(ToolsQAPage.class);
	   }
	   
	   /**
	   * Check data in table.
	   * 
	   * @param data
	   *          value that should be searched for in table 
	   * 
	   */ 
	   public void checkData(String data) {
		$(".tsc_table_s13").should(exist).shouldHave(text(data));
	   }
}
