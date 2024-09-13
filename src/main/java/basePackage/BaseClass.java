package basePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
public static  WebDriver driver;
	
	public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

	public static WebDriver launchBrowserMethod(String browser) {
		try {
		switch (browser) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();					
			driver = new ChromeDriver();
			break;
		case "Edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("browser not installed in your system");
		}
		}catch(Exception e) {
			
		}
		threadLocalDriver.set(driver);
		return getDriver();
	}
	public static synchronized WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	public static void maximizeWindowMethod() {
		try {
		driver.manage().window().maximize();
		}catch(Exception e) {}
	}

	public static void deleteAllCookiesMethod() {
		try {
		driver.manage().deleteAllCookies();
		}catch(Exception e) {}
	}
		
	public static void implicitlyWaitMethod(int timeOuts){
		try {
			driver.manage().timeouts().implicitlyWait(timeOuts, TimeUnit.SECONDS);
			}catch(Exception e) {}
	}
	
	public static void staticWaitMethod(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
		}
	}
	public static String getTitleMethod() {
		try {
		return driver.getTitle();
		}catch(Exception e) {}
		
		return null;
	}
	
	public static String passwordDecodeMethod(String decodePassword) {
		try {
			return (new String(Base64.getDecoder().decode(decodePassword.getBytes())));
		} catch (Exception e) {

		}
		return null;
	}
	public static void sendKeysMethod(WebElement sendKeysElement,String values) {
		try {
		 sendKeysElement.sendKeys(values);
		 }catch(Exception e) 
		{}
	}
	public static void sendKeysMethod(WebElement sendKeysElement,Keys value ) {
		try {
		 sendKeysElement.sendKeys(value);
		 }catch(Exception e) 
		{}
	}
	
	public static String readPropertyFile(String getPropertyKey) {
		try {
			FileInputStream readFileInput = new FileInputStream(System.getProperty("user.dir")+ "\\utilities\\TestData.properties");
			Properties propertyfile = new Properties();
			try {
				propertyfile.load(readFileInput);
			} catch (IOException e) {	
			}
			 return propertyfile.getProperty(getPropertyKey);
			
		} catch (FileNotFoundException e) {
			
		}
		return null;
	}
	public static String excelFileValue(String sheetName,int rowType, int cellType) throws IOException {
		File filename = new File(System.getProperty("user.dir")+ "");
		FileInputStream fis = new FileInputStream(filename);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		DataFormatter dataformat = new DataFormatter();
		XSSFSheet worksheetAt = workbook.getSheet(sheetName);
		Cell cellValue = worksheetAt.getRow(rowType).getCell(cellType);
		return dataformat.formatCellValue(cellValue);
	}
	
	public static void clickMethod(WebElement clickElement ) {			
			clickElement.click();
	}
	public static void forEachMethod(List<WebElement> listDropDown, String userValue) {
		for (WebElement selectDropDown :listDropDown ) {
			if(selectDropDown.getText().equals(userValue)) {
				selectDropDown.click();
				break;
			}
		}
	}
	public static void forEachAttributeMethod(List<WebElement> listDropDown, String userValue) {
		for (WebElement selectDropDown :listDropDown ) {
			if(selectDropDown.getAttribute("value").equals(userValue)) {
				selectDropDown.click();
				break;
			}
		}
	}
	
	public static void forMethod(List<WebElement> dropDownlist,int lastRowNumValue,String userValue) {
		for(int i=0;i<lastRowNumValue;i++) {
			if(dropDownlist.get(i).getText().equalsIgnoreCase(userValue)) {
				dropDownlist.get(i).click();
			}
		}
	}
	public static void switchWindowMethod() {
		String parentWindow = driver.getWindowHandle();
		Set<String> totalwindowhandles = driver.getWindowHandles();
		Iterator<String> iterator = totalwindowhandles.iterator();
		while(iterator.hasNext()) {
			String childWindow =iterator.next();
			if(!parentWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
			}
		}
	}
	public static void clearMethod(WebElement clearElement) {
		try {
			clearElement.clear();
		}catch(Exception e) {}
		
	}
	public static void browserCloseMethod() {
		try {
		driver.close();
		}catch(Exception e) {
			
		}
	}

	public static void browserQuitMethod() {
		try {
		driver.quit();
		}catch(Exception e) {}
	}

	public static void navigationMethod(String browserUrl) {
		try {
		driver.navigate().to(browserUrl);
		}catch(Exception e) {
			
		}
	}

	public static void navigationMethod(char navigate) {
		try {
		switch (navigate) {
		case 'F':
			driver.navigate().forward();
			break;
		case 'B':
			driver.navigate().back();
			break;
		default:
			driver.navigate().refresh();
		}
		}catch(Exception e) {}
	}

	public static void getBrowserMethod(String browserUrl) {
		try {
		driver.get(browserUrl);
		}catch(Exception e) {
		}
	}

	public static void alertHandlingMethod(boolean confirmation) {
		try {
		Alert alertMessage = driver.switchTo().alert();
		if (confirmation) {
			alertMessage.accept();
		} else {
			alertMessage.dismiss();
		}
		}catch(Exception e) {}
	}

	public static void alertHandlingMethod(String alertSendKeys) {
		try {
		driver.switchTo().alert().sendKeys(alertSendKeys);
		}catch(Exception e) {}
	}

	public static String alertHandlingMethod() {
		try {
		return driver.switchTo().alert().getText();
		}catch(Exception e) {}
		return null;
	}
	public static boolean isEnabledMethod(WebElement isEnableWebElement) {
		 return isEnableWebElement.isEnabled();	
	}
	public static boolean isdisplayedMethod(WebElement isDisplayWebElement) {
		
		return isDisplayWebElement.isDisplayed();
		
	}
	
	public static boolean containsMethod(String acutal,String expected) {
		return acutal.contains(expected);
	}
	
	public static int listSizeMethod(List<WebElement> element) {
		return element.size();
	}
	public static String getTextMethod(WebElement textElement) {
		return textElement.getText();
	}
	public static boolean isSelectedMethod(WebElement isSelectWebElement) {	
		return isSelectWebElement.isSelected();
	}
	public static void submitMethod(WebElement element) {
		element.submit();
	}
	public static void takesScreenShotMethod() throws IOException {
		try {
	File originScreenShotFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(originScreenShotFile, new File(System.getProperty("user.dir") +"//ScreenShots//ScreenShot"+ System.currentTimeMillis()+".png"));
	}catch(Exception e) {}
	}

	public static WebDriverWait explicitWaitMethod(int timeUnits){
		try {
		WebDriverWait wait = new WebDriverWait(driver,timeUnits);
		return wait;
		}catch(Exception e) {}
		return null;
	}
	public static void fluentWaitMethod(Duration timeouts,Duration pollingTimeOuts) {
		try {
		Wait wait = new FluentWait(driver).withTimeout(timeouts).pollingEvery(pollingTimeOuts).ignoring(Exception.class);
		}catch(Exception e) {}
	}
	public static void excuteScriptWindowSrollDownMethod(int horizontal,int vertical) {
		JavascriptExecutor scriptScrollDown=(JavascriptExecutor) driver;
		scriptScrollDown.executeScript("window.scrollBy("+horizontal +","+vertical+")", "");
	}
	
	public static void SortingMethod(List<ProductPageClass> productList) {
        for (int i = 0; i <  productList.size() - 1; i++) {
            for (int j = 0; j <  productList.size() - i - 1; j++) {
                if (productList.get(j).getPrice() > productList.get(j + 1).getPrice()) {
                	ProductPageClass temp = productList.get(j);
                    productList.set(j, productList.get(j + 1));
                    productList.set(j + 1, temp);
                }
            }
        }
	}

}
