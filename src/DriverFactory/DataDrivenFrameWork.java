package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;

public class DataDrivenFrameWork {
	WebDriver driver;
	@BeforeTest
	public void setUp()
	{
		String launch=ERP_Functions.launchApp("http://webapp.qedge.com");
	System.out.println(launch);
	String login=ERP_Functions.verifylogin("admin", "master");
	System.out.println(login);
	}
	@Test
	public void supplierCre()throws Throwable
	{
		ExcelFileUtil xl=new ExcelFileUtil();
		int rc=xl.rowCount("Supplier");
		int cc=xl.colCount("Supplier");
		System.out.println(rc+" "+cc);
		for (int i = 1; i<=rc; i++) 
		{
			String sname=xl.getCellData("Supplier", i, 0);
			String address=xl.getCellData("Supplier", i, 1);
			String city=xl.getCellData("Supplier", i, 2);
			String country=xl.getCellData("Supplier", i, 3);
			String cperson=xl.getCellData("Supplier", i, 4);
			String pnumber=xl.getCellData("Supplier", i, 5);
			String email=xl.getCellData("Supplier", i, 6);
			String mnumber=xl.getCellData("Supplier", i, 7);
			String note=xl.getCellData("Supplier", i, 8);
			String results=ERP_Functions.verifySupplier(sname, address, city, country, cperson, pnumber, email, mnumber, note);
					xl.setCellData("Supplier", i, 9, results);
					}
	}
@AfterTest
public void tearDown()
{
ERP_Functions.closeApp();	
}
}
