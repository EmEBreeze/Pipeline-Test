package seleniumgenc.utils;

public class ShopifyFormData {
	private String firstname;
	private String lastname;
	private String username;
	private City city;
	private Gender gender;
	private String password;
	private String tableText;
	private String error;
	
	public String getFirstname() { return firstname; }
	public void setFirstname(String firstname) { this.firstname = firstname; }
	
	public String getLastname() { return lastname; }
	public void setLastname(String lastname) { this.lastname = lastname; }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	public City getCity() { return city; }
	public void setCity(City city) { this.city = city; }
	
	public Gender getGender() { return gender; }
	public void setGender(Gender gender) { this.gender = gender; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String getTableText() { return tableText; }
	public void setTableText(String tableText) { this.tableText = tableText; }
	
	public String getError() { return error; }
	public void setError(String error) { this.error = error; }
}
