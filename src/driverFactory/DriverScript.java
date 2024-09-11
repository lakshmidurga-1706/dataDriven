package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil{
ExtentReports reports;
ExtentTest logger;
String Inputpath="./FileInput/TestData_lyst1724478607361.xlsx";
String Outputpath="./FileOutput/DataDrivenResults.xlsx";
@Test
public void StartTest() throws Throwable
{
	//Define path of html report
	reports=new ExtentReports("./Reports/Login.html");
	//create reference object for excel file util class
	ExcelFileUtil xl=new ExcelFileUtil(Inputpath);
	//count no of rows in login sheet
	int rc=xl.rowCount("Login");
	Reporter.log("No of rows are:"+rc,true);
	//itterate all rows in login sheet
	for(int i=1;i<=rc;i++)
	{
		logger=reports.startTest("Login Test");
		logger.assignAuthor("Lakshmi");
		//read username and password cell
		String username=xl.getCellData("Login", i, 0);
		String password=xl.getCellData("Login", i, 1);
		logger.log(LogStatus.INFO,username+" "+ password);
		//cell login method and assign parametes
		boolean res=FunctionLibrary.AdminLogin(username, password);
		if(res)
		{
			//if res is true write as login success into results cell
			xl.setCellData("Login", i, 2,"Login success",Outputpath);
			//if res is true write as pass into status cell
			xl.setCellData("Login", i, 3, "Pass", Outputpath);
			logger.log(LogStatus.PASS,"Valid username and password");
		}
		else
		{
			//adding screenshot for fail test case
			File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//copy screenshot into local syatem
			FileUtils.copyFile(screen,new File("./screenshot/Iteration/"+i+"Login.png"));
			//if res is false write as login fail into results cell
			xl.setCellData("Login", i, 2,"Login fail",Outputpath);
			//if res is fail write as pass into status cell
			xl.setCellData("Login", i, 3, "fail", Outputpath);
			logger.log(LogStatus.FAIL,"Invalid username and password");
		}
		reports.endTest(logger);
		reports.flush();
	}
}
}
