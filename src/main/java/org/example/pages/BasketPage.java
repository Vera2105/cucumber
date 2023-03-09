package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class BasketPage extends BasePage{
    WebElement deliveryCost = driver.findElement(By.xpath("//html/body/div[3]/div[6]/div[2]/div[2]/div[1]/div[2]/dl[1]/dd"));
    WebElement totalCost = driver.findElement(By.xpath("/html/body/div[3]/div[6]/div[2]/div[2]/div[1]/div[2]/dl[2]/dd"));
    WebElement checkOutButton;
    private final String TITLE = "Basket page";
    public String getTITLE() {
        return TITLE;
    }
    public BasketPage(WebDriver driver) {
        super(driver);
    }
    public String checkDataOfDeliveryCost() {
        return deliveryCost.getText();
    }
    public String checkDataOfTotal(){
        return totalCost.getText();
    }
    public void getButton(String buttonCheckOutName){
        String xpath ="//a[contains(text(),'buttonToChange')][1]".replace("buttonToChange", buttonCheckOutName);
        checkOutButton = driver.findElement(By.xpath(xpath));
    }
    public PaymentPage navigateToPaymentPage(){
        checkOutButton.click();
        return new PaymentPage(driver);
    }
}
