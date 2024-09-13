package testPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import basePackage.BaseClass;
import basePackage.ListnersInterface;
import basePackage.PageObjectAmazon;
import basePackage.ProductPageClass;

@Listeners(ListnersInterface.class)
public class TestClass extends BaseClass {
	
	public static WebDriver driver;
	public static PageObjectAmazon pageObjectClass;
	public static String productName;
	public static String productAmount;
	public static List<ProductPageClass> productList = new ArrayList<>();
	static SoftAssert assertSoft = new SoftAssert();
	public static Logger log;
	
	@BeforeSuite
	public static Logger logger() {
		log = Logger.getLogger("Amazon");
		PropertyConfigurator.configure("log4j.properties");
		log.setLevel(Level.DEBUG);
		return log;
		
	}
	@Test(priority=1)
	public static void titleMethod() throws IOException {
		driver = launchBrowserMethod(readPropertyFile("Browser"));
		pageObjectClass= new PageObjectAmazon(driver);
		staticWaitMethod(2000);
		log.info("************ Launch Chrome Browser*********");
		maximizeWindowMethod();
		deleteAllCookiesMethod();
		staticWaitMethod(2000);
		getBrowserMethod(readPropertyFile("URL"));
		staticWaitMethod(2000);
		log.info("************ Amazon Application launch*********");
		assertSoft.assertEquals(driver.getTitle(), readPropertyFile("Title"));
		log.info("************ Application Title Verify SuccessFully*********");
		
	} 
	@Test(priority=2)
	public static void searchProductMethod() throws Exception{
			try {
				sendKeysMethod(pageObjectClass.getSearchTextBox(),readPropertyFile("ProductSearch"));
				log.info("************ SearchProduct Entered in Application *********");
				staticWaitMethod(2000);
				} catch(Exception e) 
			{e.printStackTrace();}
				clickMethod(pageObjectClass.getSearchButton());
				staticWaitMethod(2000);	
				log.info("************ List of product display based on SearchResult SuccessFully*********");
	}
	@Test(priority=3)
	public static void listOfAllProductNameAndAmount() throws Exception{	
		for(int i=0;i< listSizeMethod(pageObjectClass.getListOfProductNames());i++) {
			productName= getTextMethod(pageObjectClass.getListOfProductNames().get(i));
			int priceValue= 0;
			if(i < listSizeMethod(pageObjectClass.getListOfProductAmount())) {
				if(pageObjectClass.getListOfProductAmount().get(i).isDisplayed()) {
					try {
					priceValue =Integer.parseInt(getTextMethod(pageObjectClass.getListOfProductAmount().get(i)).replace(",", ""));
				}catch(Exception e) {
					priceValue =0;}
				}
				else {
					priceValue =0;
				}
			}
			productList.add(new ProductPageClass(productName,priceValue));
		}		
		SortingMethod(productList);
		System.out.println("Products sorted by price:");
		
		log.info("************ Products sorted by Price Successfully*********");
        for (ProductPageClass product : productList) {
            System.out.println(product.getPrice()+"    |    " + product.getName());
        }
        log.info("************ Product Names and corresponding Price display successfully*********");
	}
	
	@Test(priority=4)
	public static void closeApplicationMethod() {
		browserQuitMethod();
		log.info("************ Application close SuccessFully*********");
	}

}
