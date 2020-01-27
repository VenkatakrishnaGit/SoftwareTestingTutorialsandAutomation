package com.STTA.testCases;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;


public class BaseClass {
	
	
	public static WebDriver driver;
	public static Logger logger;
	//public static ATUTestRecorder recorder;
	
	@Parameters("browser")
	@BeforeClass
	public void setUp(String br) 
	{
		//DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		//  Date date = new Date();
		//Created object of ATUTestRecorder
		  //Provide path to store videos and file name format.
		 // recorder = new ATUTestRecorder("C:\\Users\\VenkatakrishnaY\\eclipse-workspace\\SoftwareTestingTutorialsandAutomation\\ScriptRecordVedio","TestVideo-"+dateFormat.format(date), false);
		//To start video recording.
		 // recorder.start();  
		  
		logger = Logger.getLogger("SoftwareTestingTutorialsandAutomation");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver(); 
		
		driver.manage().window().maximize();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver(); 
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
	}
	
	@AfterClass
	public void tearDown() 
	{
		driver.quit();
		//To stop video recording.
		 // recorder.stop();
	}
	
	//Below method for Capture the sreenshot when script is failed
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	//Below method for datadriven testing
	@DataProvider
	 public Object[][] LoginCredentials(){
	  //Created two dimensional array with 4 rows and 2 columns.
	  //4 rows represents test has to run 4 times.
	  //2 columns represents 2 data parameters.
	  Object[][] Cred = new Object[4][2];
	  
	  Cred[0][0] = "UserId1";
	  Cred[0][1] = "Pass1";
	  
	  Cred[1][0] = "UserId2";
	  Cred[1][1] = "Pass2";
	  
	  Cred[2][0] = "UserId3";
	  Cred[2][1] = "Pass3";
	  
	  Cred[3][0] = "UserId4";
	  Cred[3][1] = "Pass4";
	  return Cred; //Returned Cred
	 }
	
	//Below method for Highlighting the Elements
	public void HighlightMyElement(WebElement element) { 
		  for (int i = 0; i < 10; i++) 
		  { 
		   JavascriptExecutor javascript = (JavascriptExecutor) driver;
		   javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: orange; border: 4px solid orange;");
		   javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: pink; border: 4px solid pink;");
		   javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 4px solid yellow;");
		   javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, ""); 
		   } 
		  } 
	
	
	
	

}
