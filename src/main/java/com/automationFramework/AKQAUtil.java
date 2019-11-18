package com.automationFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AKQAUtil {
	
	
	public static WebElement dynamicWait(WebDriver driver , WebElement element)
	{	
		WebElement dynamicElement = (new WebDriverWait(driver, 10))
  			  .until(ExpectedConditions.elementToBeClickable(element));
		
		return dynamicElement;
	}
}
