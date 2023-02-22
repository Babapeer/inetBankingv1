package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;

public class Tc_LoginTest_001 extends BaseClass
{
	   @Test
       public void LoginTest() throws IOException 
       {
		   
    	   
    	   
    	   logger.info("URL IS OPENED..........");
    	   
    	   LoginPage lp = new LoginPage(driver); //calling pageobject action method objects
    	   lp.setusername(username);
    	   logger.info("Entered USERNAME........");
    	   
    	   lp.setpassword(password);
    	   logger.info("Entered PASSWORD........");
    	   
    	   lp.clickSubmit();
    	   
    	   if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
    	   {
    		   Assert.assertTrue(true);
    		   logger.info("Login test passed......");
    	   }
    	   else
    	   {
    		   captureScreen( driver, "LoginTest");
    		   Assert.assertTrue(false);
    		   logger.info("Login test failed.......");
    	   }
       }
}
