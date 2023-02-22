package com.inetBanking.testCases;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.AddCustomerPage;
import com.inetBanking.pageObjects.LoginPage;

public class Tc_AddCustomer_003 extends BaseClass
{
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setusername(username);
		logger.info("User name is provided");
		lp.setpassword(password);
		logger.info("Password is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust = new AddCustomerPage(driver);
		
		addcust.clickAddNewCustomer();
		logger.info("providing customer details..........");
		
		addcust.custName("pavan");
		addcust.custgender("male");
		addcust.custdob("10", "15", "1992");
		Thread.sleep(3000);
		addcust.custaddress("INDIA");
		addcust.custcity("HYD");
		addcust.custstate("AP");
		addcust.custpinno("5000074");
		addcust.custtelephone("987890091");
		
		String email=randomeString()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custSubmit();
		
		Thread.sleep(3000);
		logger.info("validation started........");
		
		boolean result=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(result==true)
		{
			Assert.assertTrue(true);
			logger.info("testcase passed.......");
		}
		else
		{
			logger.info("testcase failed.........");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
		
		
	}
     
	
}
