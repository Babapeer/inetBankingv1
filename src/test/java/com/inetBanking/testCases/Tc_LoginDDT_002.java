package com.inetBanking.testCases;
//DataDrivenFramework

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.Utilities.XLUtils;
import com.inetBanking.pageObjects.LoginPage;

public class Tc_LoginDDT_002 extends BaseClass                    //DataDrivenFramework
{
	
	@Test(dataProvider="LoginData")
	public void loginDDT(String user,String pwd) throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setusername(user);
		logger.info("user name provided");
		lp.setpassword(pwd);
		logger.info("password provided");
		lp.clickSubmit();
		Thread.sleep(3000);
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
		}
		
		
	}
	
	public boolean isAlertPresent() // user defined method is created to check alert is present or not
	{
		try {
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetBanking/testData/LoginData.xlsx";
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][] =new String[rownum][colcount];
		
		for(int i=1; i<=rownum; i++)
		{
			for(int j=0; j<colcount; j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j); //1 0
				
				
			}
		}
		return logindata;
	}
	

}
