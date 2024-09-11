package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Logoutpage {
	@FindBy(xpath="(//a[.=' Logout'])[2]")
	WebElement logoutclick;
	public void Adminlogout()
	{
		logoutclick.click();
	}

}
