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
    WebElement fullNameDeliveryAddress = driver.findElement(By.xpath("//input[@name ='delivery-fullName']"));

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

    public void setFullNameDeliveryAddress(String realName) {
        this.fullNameDeliveryAddress.sendKeys(realName);
    }

    public void setDeliveryCountry(String country) {
        WebElement deliveryCountryDropdown = driver.findElement(By.id("deliveryCountryDropdown"));
        deliveryCountryDropdown.click();
        WebElement deliveryCountrySelect = driver.findElement(By.xpath("//*[@id=\"deliveryCountryDropdown\"]/ul"));
        String xPath = "//a[contains(text(), 'countryToChange')]".replace("countryToChange", country);
        WebElement selectedOption = deliveryCountrySelect.findElement(By.xpath(xPath));
        selectedOption.click();
    }

    public void setAddressLine1(String setAddressLine1) {
        WebElement addressLine1 = driver.findElement(By.xpath("//input[@name ='delivery-addressLine1']"));
        addressLine1.sendKeys(setAddressLine1);
    }

    public void setAddressLine2(String setAddressLine2) {
        WebElement addressLine2 = driver.findElement(By.xpath("//input[@name ='delivery-addressLine2']"));
        addressLine2.sendKeys(setAddressLine2);
    }

    public void setTownCity(String setTownCity) {
        WebElement townCity = driver.findElement(By.xpath("//input[@name ='delivery-city']"));
        townCity.sendKeys(setTownCity);
    }

    public void setCountyState(String setCountyState) {
        WebElement countyState = driver.findElement(By.xpath("//input[@name ='delivery-county']"));
        countyState.sendKeys(setCountyState);
    }

    public void setPostcode(String setPostcode) {
        WebElement postcode = driver.findElement(By.xpath("//input[@name ='delivery-postCode']"));
        postcode.sendKeys(setPostcode);
    }

    public void setPhoneCode(String telCode) {
        WebElement phoneCode = driver.findElement(By.id("phonePrefix"));
        phoneCode.click();
        WebElement phoneCodeSelect = driver.findElement(By.xpath("//*[@id=\"phonePrefix\"]/ul"));
        String xpath = "//a[contains(text(),'phoneCodeToChange')]".replace("phoneCodeToChange", telCode);
        WebElement selectedPhoneCode = phoneCodeSelect.findElement(By.xpath(xpath));
        selectedPhoneCode.click();
    }

    public void setPhoneNumber(String phoneNumber) {
        WebElement phoneNumberInput = driver.findElement(By.xpath("//input[@name='delivery-telephone']"));
        phoneNumberInput.sendKeys(phoneNumber);
    }
}
