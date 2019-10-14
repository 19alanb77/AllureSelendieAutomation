package selenideTest;

import static com.codeborne.selenide.Condition.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import selenideConfig.ConfigKeys;
import selenidePage.GooglePage;
import selenidePage.ToolsQAPage;

/**
 * Class that allows to create example test using Selenide.
 * 
 * @author Alan Buda
 */ 
public class SelenideTest {

	public String[] data = ConfigKeys.csvReader(ConfigKeys.getConfigKey("SelenideTest"));
	private static ToolsQAPage tools = new ToolsQAPage();
	private static GooglePage google = new GooglePage();
	
	@BeforeClass(description = "Initialization of ChromeDriver properties")
	public static void init() {
		GooglePage.setDriver();
	}
	
	@Test(description = "Find automation practice form in Google")
	public void googleSearch() {		
        google.openHomepage().searchFor("automation practice form");
        google.results().get(0).shouldHave(text("Demo Form for practicing Selenium Automation - ToolsQA")).click();
	}
	
	@Test(description = "Insert personal data",dependsOnMethods = "googleSearch")
	public void sendPersonalData() {
		tools.insertName(data[0]);
		tools.insertSurname(data[1]);
		tools.pickRadioButtons(data[2],data[3]);
	}
	
	@Test(description = "Insert date and upload example picture", dependsOnMethods = "sendPersonalData")
	public void chooseDateAndPicture() {
		tools.chooseDateAndPicture(data[4],data[5]);
	}
	
	@Test(description = "Download file and pick checkbox", dependsOnMethods = "chooseDateAndPicture")
	public void downloadFramework() {
		tools.downloadFramework(data[6]);
	}
	
	@Test(description = "Pick positions from listbox", dependsOnMethods = "downloadFramework")
	public void pickFromListboxes() {
		tools.pickFromListboxes(data[7],data[8]);
	}
	
	@Test(description = "Assert text and send form", dependsOnMethods = "pickFromListboxes")
	public void sendForm() {
		tools.sendForm();
	}
	
	@Test(description = "Open another page and check data", dependsOnMethods = "sendForm")
	public void checkTable() {
		tools.clickNewPage();
		tools.checkData(data[9]);
	}
}
