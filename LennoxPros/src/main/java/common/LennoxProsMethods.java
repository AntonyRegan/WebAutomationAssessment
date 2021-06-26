package common;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import utils.DataInputProvider;

public class LennoxProsMethods extends SeleniumMethods{
	public String browserName;
	public String dataSheetName;
	public String linkName;
	public String pageNavigation;
	public String pageDescription;
	
	@BeforeSuite
	public void initializeReport() {
		startResult();
	}
	
	@BeforeClass
	public void initializeTestModule() {
		startTestModule(testCaseName, testDescription);
	}
	
	@BeforeMethod
	public void beforeMethod(){
		test = startTestCase(testNodes);
		//test.assignCategory(category);
		//test.assignAuthor(authors);
		//System.out.println("Lennox Pros methods link name == "+DataInputProvider.getCellData("LennoxPros", "LinkNme", 2));
		startApp(browserName);
	}
	
	@AfterSuite
	public void afterSuite(){
		endResult();
	}
	
	@AfterMethod
	public void afterMethod(){
		closeAllBrowsers();
	}
	
	@DataProvider(name="fetchData")
	public  Object[][] getData(){
		DataInputProvider data = new DataInputProvider();
		return data.getTestData(dataSheetName);		
	}
}
