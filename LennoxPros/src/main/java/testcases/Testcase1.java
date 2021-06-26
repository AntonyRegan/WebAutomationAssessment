package testcases;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.LennoxProsMethods;
import pages.LennoxProsLaunchPage;
import utils.DataInputProvider;

public class Testcase1 extends LennoxProsMethods{
	DataInputProvider data = new DataInputProvider();
	String browserNameData = data.getCellData("LennoxPros", "Browser", 2);
	String testCaseID = data.getCellData("LennoxPros", "TestCaseID", 2);
	String testCaseDesc = data.getCellData("LennoxPros", "TestCaseDescription", 2);
	
	  @BeforeTest() 
	  public void setData() { 
		  testCaseName=testCaseID;
		  testDescription=testCaseDesc; 
		  testNodes="Test LennoxPros";
		  browserName = browserNameData;
		  dataSheetName="LennoxPros"; 
	  }
	 
	
	@Test(dataProvider = "fetchData")
	public void testCase(String execution, String browser, String testid, String tescaseDesc, String emailId, String password, String linkNameData, String pageNavigationData, String pageDesc, String catNo) {
		new LennoxProsLaunchPage().clickSignIn()
		.enterUserName(emailId)
		.enterPassword(password)
		.clickSignInBtn()
		.clickHamBurgerMenu()
		.clickMainMenu()
		.clickSubMenu()
		.clickCompressorsLink()
		.verifyDescription()
		.searchProduct()
		.productComparison();
		
	}
	
}
