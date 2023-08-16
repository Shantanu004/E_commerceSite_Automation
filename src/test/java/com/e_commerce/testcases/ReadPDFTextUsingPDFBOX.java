package com.e_commerce.testcases;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadPDFTextUsingPDFBOX {

	WebDriver driver;
	String url ="https://www.adobe.com/support/products/enterprise/knowledgecenter/media/c4611_sample_explain.pdf";
	@BeforeTest
	public void browserSetup() {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void pdfReaderTest() throws IOException {
		
		URL pdfUrl = new URL(url);
		InputStream is = pdfUrl.openStream();
		BufferedInputStream bis = new  BufferedInputStream(is);
		PDDocument pdDocument = PDDocument.load(bis);
		
		//Pdf Page Count
		int pdfPageCount = pdDocument.getNumberOfPages();
		System.out.println("Number of Page in PDf File:" + pdfPageCount);
		Assert.assertEquals(pdfPageCount, 4);
		
		//Get Pdf File's Content/Text
		PDFTextStripper pdfTextStripper = new PDFTextStripper();
		String pdfText = pdfTextStripper.getText(pdDocument);
		System.out.println(pdfText);
		Assert.assertTrue(pdfText.contains("PDF BOOKMARK SAMPLE"));
		Assert.assertTrue(pdfText.contains("^reformat trunc"));
		Assert.assertTrue(pdfText.contains("trans_amount=1,D"));
		Assert.assertTrue(pdfText.contains("-abmkap_bookmark.bmk -abmsinvoices"));
		
		//Set the page number and get text
		pdfTextStripper.setStartPage(4);
		pdfTextStripper.setEndPage(4);
		String pdfText1 = pdfTextStripper.getText(pdDocument);
		System.out.println(pdfText1);
		Assert.assertTrue(pdfText.contains("ap_bookmark.dat"));
	}
	
	
	
	@AfterMethod	
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}
}
