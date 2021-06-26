package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import common.LennoxProsMethods;
import utils.DataInputProvider;

public class HomePage extends LennoxProsMethods{
	DataInputProvider data = new DataInputProvider();
	String lnkName = data.getCellData("LennoxPros", "LinkName", 2);
	String pageNav = data.getCellData("LennoxPros", "PageNavigation", 2).split(":")[0];
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'hmaburger')]/a")
	private WebElement hamBurgerMenu;
	
	public HomePage clickHamBurgerMenu() {
		click(hamBurgerMenu);
		return this;
	}
	
	By mainMenu = By.xpath("(//a[text()='"+lnkName+"'])[2]");
	public HomePage clickMainMenu() {
		click(locateElement(mainMenu));
		return this;
	}
	/*
	 * @FindBy(how = How.XPATH, using = "(//a[text()='Parts and Supplies'])[2]")
	 * private WebElement mainMenu; public HomePage clickMainMenu() {
	 * click(mainMenu); return this; }
	 */
	
	//WebElement subMenu = (WebElement)By.xpath("(//a[text()='"+pageNavigation.split(":")[0]+"'])[2]");
	By subMenu = By.xpath("(//a[text()='"+pageNav+"'])[2]");
	
	public CompressorsPage clickSubMenu() {
		click(locateElement(subMenu));
		return new CompressorsPage();
	}
}
