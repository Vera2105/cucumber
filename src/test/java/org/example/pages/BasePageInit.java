package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BasePageInit {
    WebDriver driver;

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://bookdepository.com");
    }

    public void deleteCookies() {
        driver.manage().deleteAllCookies();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public HomePage navigateToHomePage(String url) {
        driver.get(url);
        return new HomePage(driver);
    }

    public void tearDown() {
        driver.quit();
    }

}
