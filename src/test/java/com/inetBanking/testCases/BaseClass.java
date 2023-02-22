package com.inetBanking.testCases;




import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import com.inetBanking.Utilities.ReadConfig;


	public class BaseClass 
	{
		ReadConfig readconfig = new ReadConfig(); //By using these objects we can call all the methods
		
		public String baseURL=readconfig.getApplicationURL();
		public String username=readconfig.getUsername();
		public String password=readconfig.getPassword();
		public static WebDriver driver;
		
		public static Logger logger;
		
		@Parameters("browser")
		@BeforeClass
		
		public void setup(String br)
		{			
			logger = Logger.getLogger("ebanking");
			PropertyConfigurator.configure("Log4j.properties");
			
			if(br.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",readconfig.getChromepath());
				driver=new ChromeDriver();
			}
			else if(br.equals("firefox"))
			{
				System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxpath());
				driver = new FirefoxDriver();
			}
			else if(br.equals("ie"))
			{
				System.setProperty("webdriver.ie.driver",readconfig.getIEpath());
				driver = new InternetExplorerDriver();
			}
			
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.get(baseURL);
			driver.manage().window().maximize();
		}
		
		@AfterClass
		public void tearDown()
		{
			driver.quit();
		}
		public void captureScreen(WebDriver driver, String screenshot) throws IOException {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File target = new File(System.getProperty("user.dir") + "/Screenshots/" + screenshot + ".png");
			FileUtils.copyFile(source, target);
			System.out.println("Screenshot taken"); // user defined methods should be called whenever testcase is failed
		}
		public String randomeString() 
		{
			String generatedstring = RandomStringUtils.randomAlphabetic(8); //it will generate string
			return(generatedstring);
		}
		public String randomeNum() //UserDefinedFunctions
		{
			String generatedstring2 = RandomStringUtils.randomNumeric(4); //it will generate num with given num4
			return(generatedstring2);
		}
	}


