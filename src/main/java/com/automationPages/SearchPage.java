package com.automationPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	
    @FindBy(xpath ="//span[@class = 'responsive-search-title__count']")
    private WebElement searchCount;
    
    @FindBy(xpath ="//article[@class = 'codified-product-tile hproduct ']")
    private WebElement productDetail;
    
    @FindBy(xpath ="//article[@class = 'codified-product-tile hproduct']")
    private WebElement productID;
  
    
    
    public SearchPage(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
    
    /*Validate SearchPage is loaded or not*/
    public boolean validateSearchPage()
    {
    	// If in search page count is greater than 0 then proceed else fail.
    	if (Integer.parseInt(searchCount.getText()) > 0) 
    		return true;
    	else
    		return false;
    }
    
    public ProductDetailsPage clickProduct()
    {
    	productDetail.click(); //Clicks on the first item
    	ProductDetailsPage productDetailPage = new ProductDetailsPage(driver);
    	return productDetailPage;
    }
    
    public String getProductId()
    {
    	// This will return the SKU ID of the item for further validations
    	return productDetail.getAttribute("data-product-id");
    }
}
