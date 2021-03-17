package seleniumgenc.pages;

import seleniumgenc.utils.Gender;

public class ShopifyRegisterPageBuilder {
	private ShopifyRegisterPage srp;
	
	public ShopifyRegisterPageBuilder(ShopifyRegisterPage srp) {
		this.srp = srp;
	}
	
	public ShopifyRegisterPageBuilder withFirstName(String firstname)
	{
		srp.getFirstNameTextbox().sendKeys(firstname);
		return this;
	}
	
	public ShopifyRegisterPageBuilder withLastName(String lastname)
	{
		srp.getLastNameTextbox().sendKeys(lastname);
		return this;
	}
	
	public ShopifyRegisterPageBuilder withUserName(String username)
	{
		srp.getUserNameTextbox().sendKeys(username);
		return this;
	}
	
	public ShopifyRegisterPageBuilder withCity(String city)
	{
		srp.getCitiesDropDown().selectByVisibleText(city);
		return this;
	}
	
	public ShopifyRegisterPageBuilder withPassword(String password)
	{
		srp.getPasswordTextbox().sendKeys(password);
		return this;
	}
	
	public ShopifyRegisterPageBuilder withGender(Gender gender)
	{
		if(gender == Gender.Male)
		{
			srp.getMaleRadio().click();
		}
		else if(gender == Gender.Female)
		{
			srp.getFemaleRadio().click();
		}
		else if(gender == Gender.Other)
		{
			srp.getOtherRadio().click();
		}
		
		return this;
	}
	
	public ShopifyRegisterPage build() { return srp; }
	
	public void quitDriver() { srp.getDriver().quit(); }
}
