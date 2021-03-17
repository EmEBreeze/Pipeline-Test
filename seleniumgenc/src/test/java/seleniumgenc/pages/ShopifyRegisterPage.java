package seleniumgenc.pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class ShopifyRegisterPage {
	private final String BASE_URL = "https://webapps.tekstac.com/Shopify/";
	private WebDriver driver;
	
	public ShopifyRegisterPage(WebDriver driver)
	{
		this.driver = driver;
		this.driver.get(BASE_URL);
	}
	
	public WebDriver getDriver() { return driver; }
	
	public WebElement getFirstNameTextbox() { return driver.findElement(By.id("firstname")); }
	
	public WebElement getLastNameTextbox() { return driver.findElement(By.id("lastname")); }
	
	public WebElement getUserNameTextbox() { return driver.findElement(By.id("username")); }
	
	public WebElement getCitiesWebElement() { return driver.findElement(By.id("selectcity")); }
	
	public Select getCitiesDropDown() { return new Select(getCitiesWebElement()); }
	
	public WebElement getPasswordTextbox() { return driver.findElement(By.id("pass")); }
	
	public WebElement getMaleRadio() { return driver.findElement(By.xpath("//input[@value='male']")); }
	
	public WebElement getFemaleRadio() { return driver.findElement(By.xpath("//input[@value='female']")); }
	
	public WebElement getOtherRadio() { return driver.findElement(By.xpath("//input[@value='other']")); }
	
	public WebElement getRegisterButton() { return driver.findElement(By.id("reg")); }
	
	public WebElement getCancelButton() { return driver.findElement(By.id("cancel")); }
	
	public WebElement getTable() { return driver.findElement(By.id("ttab")); }
	
	public List<WebElement> getTableRows() { return  getTable().findElements(By.tagName("tr")); }
	
	public String getLastRowText() {
		List<WebElement> rows = getTableRows();
		return rows.get(rows.size() - 1).getText();
	}
	
	public WebElement getErrors() { return driver.findElement(By.id("errfn")); }
	
	public void clickRegButton() { getRegisterButton().click(); }
	
	public void clickCancelButton() { getCancelButton().click(); }
}
