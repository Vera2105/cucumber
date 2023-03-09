package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage extends BasePage {
    WebElement email = driver.findElement(By.xpath("//*[@id=\"emailAddress\"]/div[2]/div/div/input"));
    WebElement subTotalOnPaymentPage = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div[2]/dl/dd"));
    WebElement deliveryOnPaymentPage = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div[3]/dl/dd/strong"));
    WebElement vatOnPaymentPage = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div[4]/dl/dd"));
    WebElement totalOnPaymentPage = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div[5]/dl/dd"));

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public void setEmail(String email1) {
        this.email.sendKeys(email1);
    }

    public String checkDataOfSubTotalOnPaymentPage() {
        return subTotalOnPaymentPage.getText();
    }

    public String checkDataOfDeliveryOnPaymentPage() {
        return deliveryOnPaymentPage.getText();
    }

    public String checkDataOfVatOnPaymentPage() {
        return vatOnPaymentPage.getText();
    }

    public String checkDataOfTotalOnPaymentPage() {
        return totalOnPaymentPage.getText();
    }
}
