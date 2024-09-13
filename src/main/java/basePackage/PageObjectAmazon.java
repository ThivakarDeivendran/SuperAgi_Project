package basePackage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectAmazon {
	public WebDriver driver;
	public PageObjectAmazon(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);			
	}
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	private WebElement searchTextBox;
	
	public WebElement getSearchTextBox() {
		return searchTextBox;
	}
	
	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	private WebElement searchButton;
	
	public WebElement getSearchButton() {
		return searchButton;
	}
	
	@FindBy(xpath="//div[contains(@class,'result-item')]//div[contains(@class,'list-col-right')]//h2/a/span")
	private List<WebElement> listOfProductNames;
	
	public List<WebElement> getListOfProductNames() {
		return listOfProductNames;
	}
	
	@FindBy(xpath="//div[contains(@class,'result-item')]//descendant::span[contains(@class,'whole')]")
	private List<WebElement> listOfProductAmount;
	
	public List<WebElement> getListOfProductAmount() {
		return listOfProductAmount;
	}
	
	

}
