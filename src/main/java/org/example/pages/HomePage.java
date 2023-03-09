package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage extends BasePage {
        WebElement searchField = driver.findElement(By.xpath("//input[@aria-label='Search for books by keyword / title / author / ISBN']"));
        WebElement searchButton = driver.findElement(By.xpath("//button[@aria-label='Search']"));
    private final String TITLE = "Initial home page";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getTITLE() {
        return TITLE;
    }

   public WebDriver getDriver(){
        return driver;
    }
    public void  searchRequest (String searchText){
        searchField.sendKeys(searchText);
    }
    public SearchResultPage navigateToSearchResultPage (){
        searchButton.click();
        return new SearchResultPage(driver);
    }
}
