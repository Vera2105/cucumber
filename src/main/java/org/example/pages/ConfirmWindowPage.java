package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfirmWindowPage extends BasePage{
WebElement addToBasketPageButton;
    public ConfirmWindowPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getButton(String buttonName){
        String xpath ="//a[contains(text(),'buttonToChange')]".replace("buttonToChange", buttonName);
        addToBasketPageButton = driver.findElement(By.xpath(xpath));
        return this.addToBasketPageButton;
    }
   public BasketPage navigateToBasketPage(){
        addToBasketPageButton.click();
        return new BasketPage(driver);
   }
}
