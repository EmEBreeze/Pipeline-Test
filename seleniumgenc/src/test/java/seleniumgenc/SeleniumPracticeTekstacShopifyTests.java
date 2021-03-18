package seleniumgenc;
import seleniumgenc.pages.*;
import seleniumgenc.utils.*;

import java.io.IOException;
//import static org.testng.Assert.*;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumPracticeTekstacShopifyTests {
	//may not make final if need to concat other stuff to end
	private ShopifyRegisterPageBuilder srpb;
	private String excelPath = System.getProperty("user.dir") + "\\src\\test\\java\\excelFiles\\ShopifyTestData.xlsx";
	public static final String ALL_DATASHEET = "allFields";
	public static final String MISSING_DATASHEET = "missingFields";
	private WebDriver driver;

	@BeforeTest(groups={"shopifyRegister"})
	public void setUpDriver()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@BeforeMethod(groups={"shopifyRegister"})
	public void setUpBuilder()
	{
		srpb = new ShopifyRegisterPageBuilder(new ShopifyRegisterPage(driver));
	}

	@DataProvider(name = "all-sheet-data")
	public Object[][] happyPath() throws IOException
	{
		Object[][] data = ShopifyExcelUtils.readDataFromExcel(excelPath, ALL_DATASHEET).stream()
				.map(formData -> new Object[]{formData.getFirstname(), formData.getLastname(), formData.getUsername(),
						formData.getCity().toString(), formData.getGender().toString(), formData.getPassword(),
						formData.getTableText(), formData.getError()}).toArray(Object[][]::new);

		return data;
	}

	@DataProvider(name = "missing-sheet-data")
	public Object[][] unHappyPath() throws IOException
	{
		Object[][] data = ShopifyExcelUtils.readDataFromExcel(excelPath, MISSING_DATASHEET).stream()
				.map(formData -> new Object[]{formData.getFirstname(), formData.getLastname(), formData.getUsername(),
						formData.getCity().toString(), formData.getGender().toString(), formData.getPassword(),
						formData.getTableText(), formData.getError()}).toArray(Object[][]::new);

		return data;
	}

	@Test(groups={"shopifyRegister"})
	public void shopifyRegisterFormContainsAllFields()
	{
		Assert.assertTrue(srpb.build().getFirstNameTextbox().isDisplayed());
		Assert.assertTrue(srpb.build().getLastNameTextbox().isDisplayed());
		Assert.assertTrue(srpb.build().getUserNameTextbox().isDisplayed());
		Assert.assertTrue(srpb.build().getCitiesWebElement().isDisplayed());
		Assert.assertTrue(srpb.build().getPasswordTextbox().isDisplayed());
		Assert.assertTrue(srpb.build().getMaleRadio().isDisplayed());
		Assert.assertTrue(srpb.build().getFemaleRadio().isDisplayed());
		Assert.assertTrue(srpb.build().getOtherRadio().isDisplayed());
		Assert.assertTrue(srpb.build().getRegisterButton().isDisplayed());
		Assert.assertTrue(srpb.build().getCancelButton().isDisplayed());
		Assert.assertTrue(srpb.build().getTable().isDisplayed());
		// can use isEnabled() to ensure each field can be used
		// can use isSelected() for checkbox and radio button
	}

	@Test(dataProvider = "all-sheet-data",groups={"shopifyRegister"})
	public void fillOutFormAndSubmitTableCheck(String firstName, String lastName, String userName,
			String city, String gender, String password, String tableText, String error)
	{
		ShopifyRegisterPage page = srpb.withFirstName(firstName)
		.withLastName(lastName)
		.withUserName(userName)
		.withCity(city)
		.withPassword(password)
		.withGender(Gender.valueOf(gender)).build();

		page.clickRegButton();

		//WebElement table = page.getTable();
		//List<WebElement> rows = page.getTableRows();
		/*WebElement fName = table.findElement(By.xpath("//td[contains(text(),'Marley')]"));
		WebElement lName = table.findElement(By.xpath("//td[contains(text(),'Breese')]"));
		WebElement uName = table.findElement(By.xpath("//td[contains(text(),'marleybreese')]"));
		WebElement city = table.findElement(By.xpath("//tbody/tr[3]/td[4]"));

		Assert.assertEquals(fName.getText(), "Marley");
		Assert.assertEquals(lName.getText(), "Breese");
		Assert.assertEquals(uName.getText(), "marleybreese");
		Assert.assertEquals(city.getText(), "Chennai");*/
		//OR
		try
		{

		}
		catch(NoSuchElementException e)
		{

		}

		String expected = tableText;
		String actual = page.getLastRowText();
		Assert.assertEquals(expected, actual);
	}


	@Test(dataProvider = "all-sheet-data",groups={"shopifyRegister"})
	public void errorForNoFieldsAndSubmit(String firstName, String lastName, String userName,
			String city, String gender, String password, String tableText, String error)
	{
		//create a test for each blank field error
		//should put in excel sheet (inputs and expected outputs), run for every row of data
		//parameterized test, to eliminate redundancy
		if(firstName == null)
		{
			firstName = "";
		}
		if(lastName == null)
		{
			lastName = "";
		}
		if(userName == null)
		{
			userName = "";
		}
		if(password == null)
		{
			password = "";
		}

		ShopifyRegisterPage page = srpb.withFirstName(firstName)
				.withLastName(lastName)
				.withUserName(userName)
				.withCity(city)
				.withPassword(password)
				.withGender(Gender.valueOf(gender)).build();

		page.clickRegButton();
		WebElement errors = page.getErrors();

		Assert.assertTrue(errors.isDisplayed());
		Assert.assertEquals(errors.getText(),error);
	}

	@Test(groups={"shopifyRegister"})
	public void apachePOITest() throws IOException
	{
		List<ShopifyFormData> data = ShopifyExcelUtils.readDataFromExcel(excelPath, ALL_DATASHEET);

		Assert.assertEquals(data.get(0).getFirstname(), "");
	}

	@Test(groups={"shopifyRegister"})
	public void shopifyRegisterFormFieldsEnabled()
	{
		Assert.assertTrue(srpb.build().getFirstNameTextbox().isEnabled());
		Assert.assertTrue(srpb.build().getLastNameTextbox().isEnabled());
		Assert.assertTrue(srpb.build().getUserNameTextbox().isEnabled());
		Assert.assertTrue(srpb.build().getCitiesWebElement().isEnabled());
		Assert.assertTrue(srpb.build().getPasswordTextbox().isEnabled());
		Assert.assertTrue(srpb.build().getMaleRadio().isEnabled());
		Assert.assertTrue(srpb.build().getFemaleRadio().isEnabled());
		Assert.assertTrue(srpb.build().getOtherRadio().isEnabled());
		Assert.assertTrue(srpb.build().getRegisterButton().isEnabled());
		Assert.assertTrue(srpb.build().getCancelButton().isEnabled());
		Assert.assertTrue(srpb.build().getTable().isEnabled());
	}

	@AfterMethod(groups={"shopifyRegister"})
	public void clear()
	{
		driver.navigate().refresh();
	}

	@AfterTest(groups={"shopifyRegister"})
	public void quitAll()
	{
		srpb.quitDriver();
	}
}
