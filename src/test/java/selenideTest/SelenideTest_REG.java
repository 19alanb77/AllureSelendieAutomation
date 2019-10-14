package selenideTest;

import static com.codeborne.selenide.Condition.text;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import selenideConfig.ConfigKeys;
import selenideConfig.ExcelUtils;
import selenidePage.CostCategoryPage;
import selenidePage.CounterpartyPage;
import selenidePage.ExternalContactsPage;
import selenidePage.GlobalContactsPage;
import selenidePage.GlobalPlanPage;
import selenidePage.GooglePage;
import selenidePage.InsurerContactsPage;
import selenidePage.LineOfInsurancePage;
import selenidePage.MainPage;
import selenidePage.PolicyPage;
import selenidePage.PremiumBasisPage;
import selenidePage.ToolsQAPage;

/**
 * Class that allows to create example test using Selenide.
 * 
 * @author Alan Buda
 */ 

public class SelenideTest_REG {

	private static ToolsQAPage tools = new ToolsQAPage();
	private static GooglePage google = new GooglePage();
	
	@DataProvider(name = "data")
	public static Object[][] excelData() throws Exception{
        Object[][] testObjArray = ExcelUtils.getTableArray(ConfigKeys.getConfigKey("SelenideTest_REG"),"Sheet1");        
        return (testObjArray);
	}
	
	@BeforeClass(description = "Initialization of automation driver")
	public static void init() {
		GooglePage.setDriver();
	}

	@Test(dataProvider = "data", description = "Find and test automation practice form")
	public void googleSearch(String name,String surname,String sex,String experience,String date,String profession,String tool,String continent,String command,String value) {		
        google.openHomepage().searchFor("automation practice form");
        google.results().get(0).shouldHave(text("Demo Form for practicing Selenium Automation - ToolsQA")).click();
		tools.insertName(name);
		tools.insertSurname(surname);
		tools.pickRadioButtons(sex,experience);
		tools.chooseDateAndPicture(date,profession);
		tools.downloadFramework(tool);
		tools.pickFromListboxes(continent,command);
		tools.sendForm();
		tools.clickNewPage();
		tools.checkData(value);
	}

}
