package com.automationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationFramework.AKQAUtil;

public class ProductDetailsPage {	

WebDriver driver;

	@FindBy(xpath ="//div[@class='product-detail__ratings']/span")
	private WebElement productID;
	
    @FindBy(xpath ="//button[@class = 'bui-btn product-fulfillment__main-btn bui-btn--secondary bui-btn--mini bui-btn--block']")
    private WebElement saveToWishlist;
    
    @FindBy(xpath ="//div[@class='bui-tooltip__content']")
    private WebElement saveDialog;
    
    @FindBy(xpath ="//div[@class = 'bui-tooltip__content']//a")
    private WebElement wishListLink;
    
    @FindBy(xpath ="//div[@class = 'bui-tooltip__content']")
    private WebElement wishListDialog;
  
    public ProductDetailsPage(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
    
    /*Validate Product Detail page is loaded or not*/
    public boolean validateProductDetailPage(String SkuId)
    {
    	// Checks for save wishlist button and SKU ID(Same product should open)
    	if (saveToWishlist.isDisplayed() && productID.getText().contains(SkuId))
    		return true;
    	else
    		return false;
    }
    
    /* Click on save to wishlist button and waits for the dialog box to appear*/
    public boolean clickSaveToWishList()
    {
    	saveToWishlist.click();
    	AKQAUtil.dynamicWait(driver, saveDialog);
    	return wishListDialog.isDisplayed(); 	
    }
    
    /* Navigates to wish list page*/
    public WishListPage navigateToWishlistPage()
    {
    	// Javascript executor is used to expose HTML and look for wish list link present in dialog box
    	WebElement myelement = driver.findElement(By.xpath("//div[@class = 'bui-tooltip__content']//a"));
    	JavascriptExecutor jse2 = (JavascriptExecutor)driver;
    	jse2.executeScript("arguments[0].scrollIntoView()", myelement);
    	wishListLink.click();
    	WishListPage wishListPage = new WishListPage(driver);
    	return wishListPage;
    }
    
}
