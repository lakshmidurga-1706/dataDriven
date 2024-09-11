package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage {
	//define Repository for login
@FindBy(xpath="//button[@name='btnreset']")
WebElement Objreset;
@FindBy(id="username")
WebElement Objuser;
@FindBy(name="password")
WebElement Objpass;
@FindBy(xpath="//button[@name='btnsubmit']")
WebElement ObjLogin;
//write method to perform action
public void AdminLogin(String user,String pass)
{
	Objreset.click();
	Objuser.sendKeys(user);
	Objpass.sendKeys(pass);
	ObjLogin.click();
}


}
