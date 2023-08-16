package com.e_commerce.testcases;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadPDFinBrowser {
 
	WebDriver driver;
	
	@BeforeTest
	public void browserSetup() {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.princexml.com/samples/");
		driver.manage().window().maximize();
			
	}
	 
	@Test
	public void pdfReaderinBrowserTest() throws InterruptedException, IOException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='journal']//a[contains(text(),'PDF')]")).click();
		Thread.sleep(3000);	
		String url = driver.getCurrentUrl();
		URL pdfUrl = new URL(url);
		URLConnection urlConnection =  pdfUrl.openConnection();
		urlConnection.addRequestProperty("User-Agent", "Shan");
		InputStream is = urlConnection.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		PDDocument pdDocument = PDDocument.load(bis);
		
		//Page Count
		int pdfPageCount = pdDocument.getNumberOfPages();
		System.out.println("Number of Page in PDf File:" + pdfPageCount);
		Assert.assertEquals(pdfPageCount, 3);
		
		//Get Pdf File's Content/Text
		PDFTextStripper pdfTextStripper = new PDFTextStripper();
		pdfTextStripper.setStartPage(2);
		pdfTextStripper.setEndPage(3);
		String pdfText = pdfTextStripper.getText(pdDocument);
		System.out.println(pdfText);
		Assert.assertTrue(pdfText.contains("ZOBEL,"));
		
	}
	
	@AfterMethod	
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}
	
}
