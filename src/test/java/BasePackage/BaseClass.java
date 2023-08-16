package BasePackage;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BaseClass {
	
	public static WebDriver driver;
	public Properties prop;
	public Properties DataProp;
	
	public void loadPropertiesFile() {
		
		prop = new Properties();
		File BaseDatafile = new File(System.getProperty("user.dir") +"\\Base_Configuration");
		try {
			FileInputStream BaseDatafis = new FileInputStream(BaseDatafile);
			prop.load(BaseDatafis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		DataProp = new Properties();
		File TestDatafile = new File(System.getProperty("user.dir")+"\\TestData_Configuration");
		try {
			FileInputStream TestDatafis = new FileInputStream(TestDatafile);
			DataProp.load(TestDatafis);	
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
public  WebDriver intilizeBrowserAndOpenApplicationURL(String browserName) {

	if(browserName.equals("chrome")){
		driver = new ChromeDriver();
	}else if(browserName.equals("edge")) {
		driver = new EdgeDriver();
	} else if(browserName.equals("safari")) {
		driver = new SafariDriver();
	}

	return driver;
	}
	
//	@BeforeMethod
//	public void setup() {
//		System.setProperty("webdriver.http.factory", "jdk-http-client");
//	    loadPropertiesFile();
//		WebDriverManager.chromedriver().setup();
//	    driver = new ChromeDriver();
//        driver.get(prop.getProperty("url"));
//		driver.manage().window().maximize();
//	}
//
//	@AfterMethod
//	public void teardown() throws InterruptedException {
//	Thread.sleep(3000);
//	driver.close();
//	}

	public static String generateWithEmailTimeStamp() {

		Date date = new Date();
		String timestamp= date.toString().replace(" ", "_").replace(":", "_");
		return "shan"+timestamp+"@gmail.com";
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName) {
		XSSFWorkbook workbook = null; 
		File excelfile = new File(System.getProperty("user.dir")+"\\UserName_And_PasswordListForDataDriven.xlsx");
		try {
		FileInputStream fisExcel = new FileInputStream(excelfile);
		 workbook = new XSSFWorkbook(fisExcel); 
		}catch(Throwable e){
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i = 0;i<rows;i++) {
			XSSFRow row = sheet.getRow(i+1);
		
		      for(int j=0;j<cols;j++) {
			       XSSFCell cell= row.getCell(j);
			        CellType cellType = cell.getCellType();
			        
			        switch (cellType) { 
			        case STRING:
			        	data[i][j] = cell.getStringCellValue();
			        	break;
			        
			        case NUMERIC:
			        	data[i][j] =  Integer.toString((int)cell.getNumericCellValue());
			            break;
			        case BOOLEAN:
			        	data[i][j] = cell.getBooleanCellValue();
			        }
		      }
		}
		
		return data;
	}
	

}
