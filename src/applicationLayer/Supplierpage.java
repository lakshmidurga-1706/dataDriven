package applicationLayer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class Supplierpage {
WebDriver driver;
public Supplierpage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="(//a[contains(.,'Suppliers')])[2]")
WebElement clicksupplierlink;
@FindBy(xpath="(//span[@data-caption='Add'])[1]")
WebElement clickAddIcon;
@FindBy(name="x_Supplier_Number")
WebElement suppliernumer;
@FindBy(name="x_Supplier_Name")
WebElement suppliername;
@FindBy(name="x_Address")
WebElement address;
@FindBy(name="x_City")
WebElement city;
@FindBy(name="x_Country")
WebElement country;
@FindBy(name="x_Contact_Person")
WebElement contactperson;
@FindBy(name="x_Phone_Number")
WebElement phonenumber;
@FindBy(name="x__Email")
WebElement email;
@FindBy(name="x_Mobile_Number")
WebElement mobilenumber;
@FindBy(name="x_Notes")
WebElement notes;
@FindBy(name="btnAction")
WebElement clickAddbtn;
@FindBy(xpath="//button[@class='ajs-button btn btn-primary']")
WebElement clickconfirmokbtn;
@FindBy(xpath="//button[@class='ajs-button btn btn-primary']")
WebElement clickAlertokbtn;
@FindBy(xpath="//span[@class='glyphicon glyphicon-search ewIcon']")
WebElement clicksearchpanlebtn;
@FindBy(name="psearch")
WebElement searchtextbox;
@FindBy(name="btnsubmit")
WebElement searchbtn;
@FindBy(xpath="//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")
WebElement webtable;
//method for supplier creation
public boolean addsupplier(String Suppliername,String Address,String City, String Country, String Contactperson, 
		String PhoneNumber, String Email, String Mobilenumber, String Notes) throws Throwable
{
Actions ac=new Actions(driver);
ac.moveToElement(this.clicksupplierlink).click().perform();
Thread.sleep(1000);
ac.moveToElement(this.clickAddIcon).click().perform();
Thread.sleep(1000);
String Exp_data=this.suppliernumer.getAttribute("value");
this.suppliername.sendKeys(Suppliername);
this.address.sendKeys(Address);
this.city.sendKeys(City);
this.country.sendKeys(Country);
this.contactperson.sendKeys(Contactperson);
this.phonenumber.sendKeys(PhoneNumber);
this.email.sendKeys(Email);
this.mobilenumber.sendKeys(Mobilenumber);
this.notes.sendKeys(Notes);
this.clickAddbtn.sendKeys(Keys.ENTER);
Thread.sleep(1000);
this.clickAlertokbtn.click();
Thread.sleep(1000);
if(!this.searchtextbox.isDisplayed())
	this.clicksearchpanlebtn.click();
this.searchtextbox.clear();
this.searchtextbox.sendKeys(Exp_data);
this.searchbtn.click();
Thread.sleep(1000);
String Act_data=webtable.getText();
if(Act_data.equals(Exp_data))
{
	Reporter.log("Add Supplier is success::"+Exp_data+" "+Act_data);
	return true;
}
else
{
	Reporter.log("Add Supplier is fail::"+Exp_data+" "+Act_data);
	return false;
}
}
}
