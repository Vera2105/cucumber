package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class SearchResultPage extends BasePage {

    private final String TITLE = "Search page";
    WebElement filterPrice = driver.findElement(By.id("filterPrice"));
    WebElement filterAvailability = driver.findElement(By.id("filterAvailability"));
    WebElement filterLang = driver.findElement(By.id("filterLang"));
    WebElement filterFormat = driver.findElement(By.id("filterFormat"));
    WebElement refineResultsButton = driver.findElement(By.xpath("//button[contains(text(), 'Refine results')]"));


    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public Set<String> getListOfBooks() {
        WebElement listOfBooks = driver.findElement(By.xpath("/html/body/div[3]/div[6]/div[5]/div[4]/div/div/div/div"));
        List<WebElement> divCardsAll = listOfBooks.findElements(By.className("title"));
        Set<String> setOfBooks = divCardsAll.stream().map(el -> el.findElement(By.tagName("a")).getText()).collect(Collectors.toSet());
        return setOfBooks;
    }

    public void setDataToFilterPrice(String price) {
        Select dropdown = new Select(filterPrice);
        dropdown.selectByValue(price);
    }

    public void setDataToFilterAvailability(String availability) {
        Select dropdown = new Select(filterAvailability);
        dropdown.selectByValue(availability);
    }

    public void setDataToFilterLang(String language) {
        Select dropdown = new Select(filterLang);
        dropdown.selectByValue(language);
    }

    public void setDataToFilterFormat(String format) {
        Select dropdown = new Select(filterFormat);
        dropdown.selectByValue(format);
    }

    public void clickRefineResultsButton() {
        refineResultsButton.click();
    }

    public WebElement getSelectedBook(String title) {
        WebElement listOfBooks = driver.findElement(By.xpath("/html/body/div[3]/div[6]/div[5]/div[4]/div/div/div/div"));
        List<WebElement> divCardsAll = listOfBooks.findElements(By.className("book-item"));
        List<WebElement> filteredBooks = divCardsAll.stream().filter(el ->
        {
            WebElement elOfTitle = el.findElement((By.className("title")));
            return elOfTitle.findElement(By.tagName("a")).getText().equals(title);
        }).collect(Collectors.toList());
        return filteredBooks.get(0);
    }

    public ConfirmWindowPage addToBasket(WebElement element) {
        WebElement addToBasketButton = element.findElement(By.className("btn-wrap"));
        addToBasketButton.click();
        return new ConfirmWindowPage(driver);
    }
}