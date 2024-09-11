package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import applicationLayer.Loginpage;
import applicationLayer.Logoutpage;

public class AppUtil1 {
public static WebDriver driver;
public static Properties conpro;
@BeforeTest
public static void setup() throws Throwable
{
	conpro=new Properties();
	conpro.load(new FileInputStream("./PropertyFiles\\Environment.properties"));
	if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Loginpage login=PageFactory.initElements(driver,Loginpage.class);
		//call login method
		login.AdminLogin("admin","master");
	}
	else if(conpro.getProperty("Browser").equalsIgnoreCase("firfox"))
	{
		driver=new FirefoxDriver();
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Loginpage login=PageFactory.initElements(driver,Loginpage.class);
		//call login method
		login.AdminLogin("admin","master");
	}
}
@AfterTest
public void teardown()
{
	Logoutpage logout=PageFactory.initElements(driver, Logoutpage.class);
	driver.quit();
}
}
