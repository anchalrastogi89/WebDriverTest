package com.automationTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automationFramework.LoadConfig;
import com.automationPages.ProductDetailsPage;
import com.automationPages.SearchPage;
import com.automationPages.WishListPage;

public class AddToWishListTestCase {
	
	WebDriver driver;
	WebDriverWait wait;
	
	/*Initalize the driver and loads the website.*/
	@BeforeTest
	  public void SetDriver()
	{
			LoadConfig loadConfig = new LoadConfig();          
            System.setProperty("webdriver.chrome.driver", loadConfig.getDriverPath());
    		driver = new ChromeDriver();
    		driver.manage().window().maximize();
    		driver.get(loadConfig.getEnv());
    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	  public void addToWishlist(){
		String SkuId;
		SearchPage searchPage = new SearchPage(driver);
		Assert.assertTrue(searchPage.validateSearchPage());
		SkuId = searchPage.getProductId();
		ProductDetailsPage productDetailPage = searchPage.clickProduct();
		Assert.assertTrue(productDetailPage.validateProductDetailPage(SkuId));
		Assert.assertTrue(productDetailPage.clickSaveToWishList());
		WishListPage wishListPage = productDetailPage.navigateToWishlistPage();
		Assert.assertTrue(wishListPage.validateWishListlPage());
		Assert.assertTrue(wishListPage.validateWishListItem(SkuId));
	  }
	
	@AfterTest
	  public void afterTest() {
		driver.close(); //Terminates the driver after execution.
	  }

}
