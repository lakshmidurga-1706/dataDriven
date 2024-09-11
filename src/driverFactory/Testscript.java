package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import applicationLayer.Supplierpage;
import config.AppUtil1;
import utilities.ExcelFileUtil;

public class Testscript extends AppUtil1 {
	String Inputpath="./FileInput/SupplierData.xlsx";
	String Outputpath="./FileOutput/SupplierResults.xlsx";
	ExtentReports reports;
	ExtentTest logger;
	String Tcshhet="Supplier";
	@Test
	public void starttest() throws Throwable
	{
		reports=new ExtentReports("./ExtentReport/Supplier.html");
		ExcelFileUtil xl=new ExcelFileUtil(Inputpath);
		int rc=xl.rowCount(Tcshhet);
		Reporter.log("No fi rows are:"+rc,true);
		for (int i=1;i<=rc;i++)
		{
			logger=reports.startTest("Validate supplier test");
			String sname=xl.getCellData(Tcshhet, i, 0);
			String adress=xl.getCellData(Tcshhet, i, 1);
			String city=xl.getCellData(Tcshhet, i, 2);
			String country=xl.getCellData(Tcshhet, i, 3);
			String cperson=xl.getCellData(Tcshhet, i, 4);
			String pnumber=xl.getCellData(Tcshhet, i, 5);
			String email=xl.getCellData(Tcshhet, i, 6);
			String mnumber=xl.getCellData(Tcshhet, i, 7);
			String notes=xl.getCellData(Tcshhet, i, 8);
			logger.log(LogStatus.INFO,sname+" "+adress+" "+city+" "+country+" "+cperson+" "+pnumber+" "+ email+" "+mnumber+" "+notes);
			Supplierpage sup=PageFactory.initElements(driver,Supplierpage.class);
			boolean res=sup.addsupplier(sname, adress, city, country, cperson, pnumber, email, mnumber, notes);
			if(res)
			{
				xl.setCellData(Tcshhet,i, 9, "pass", Outputpath);
				logger.log(LogStatus.PASS,"add supplier success");
			}
				
			else
			{
				xl.setCellData(Tcshhet,i, 9, "fail", Outputpath);
				logger.log(LogStatus.PASS,"add supplier fail");	
			}
		}
	}
	

}
