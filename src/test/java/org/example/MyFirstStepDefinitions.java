package org.example;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.example.pages.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class MyFirstStepDefinitions {
    BasePageInit basePageInit;
    HomePage homePage;
    SearchResultPage searchResultPage;

    ConfirmWindowPage confirmWindowPage;
    BasketPage basketPage;
    PaymentPage paymentPage;

    @Given("I am an anonymous customer with clear cookies")
    public void i_am_an_anonymous_customer_with_clear_cookies() {
        basePageInit = new BasePageInit();
        basePageInit.setUp();
        basePageInit.deleteCookies();
    }

    @Given("I open the {string} of {string}")
    public void i_open_the(String homePageName, String url) {
        homePage = basePageInit.navigateToHomePage(url);
        String urlOfHomePage = homePage.getDriver().getCurrentUrl();
        Assertions.assertEquals(homePage.getTITLE(), homePageName);
        Assertions.assertEquals(urlOfHomePage, url);
    }

    @Given("I search for {string}")
    public void i_search_for(String searchRequest) {
        homePage.searchRequest(searchRequest);
    }

    @Given("I am redirected to a {string}")
    public void i_am_redirected_to_a(String searchPage) {
        searchResultPage = homePage.navigateToSearchResultPage();
        Assertions.assertEquals(searchResultPage.getTITLE(), searchPage);
    }

    @Given("Search results contain the following products")
    public void search_results_contain_the_following_products(List<String> listOfBooks) {
        Set<String> actualResult = searchResultPage.getListOfBooks();
        List<String> listFiltered = actualResult.stream().filter(listOfBooks::contains).collect(Collectors.toList());
        Assertions.assertTrue(listFiltered.size() == listOfBooks.size());
    }

    @Given("I apply the following search filters")
    public void i_apply_the_following_search_filters(Map<String, String> filterBlock) {
        String priceValue = filterBlock.get("Price range");
        if (priceValue.equals("30€ +")) {
            searchResultPage.setDataToFilterPrice("high");
        }
        String availabilityValue = filterBlock.get("Availability");
        if (availabilityValue.equals("In Stock (5)")) {
            searchResultPage.setDataToFilterAvailability("1");
        }
        String langValue = filterBlock.get("Language");
        if (langValue.equals("English (17)")) {
            searchResultPage.setDataToFilterLang("123");
        }
        String formatValue = filterBlock.get("Format");
        if (formatValue.equals("Paperback (22)")) {
            searchResultPage.setDataToFilterFormat("1");
        }
        searchResultPage.clickRefineResultsButton();
    }

    @Given("Search results contain only the following products")
    public void search_results_contain_only_the_following_products(List<String> listOfBooks) {
        Set<String> actualResult = searchResultPage.getListOfBooks();
        List<String> listFiltered = actualResult.stream().filter(listOfBooks::contains).collect(Collectors.toList());
        Assertions.assertTrue(listFiltered.size() == listOfBooks.size());
    }

    @Given("I click {string} button for product with name {string}")
    public void i_click_button_for_product_with_name(String buttonName, String bookName) {
        WebElement mySelectedBook = searchResultPage.getSelectedBook(bookName);
        Assertions.assertEquals(mySelectedBook.findElement(By.className("title")).findElement(By.tagName("a")).getText(), bookName);
        Assertions.assertEquals(mySelectedBook.findElement(By.className("btn-wrap")).findElement(By.tagName("a")).getText(), buttonName);
        confirmWindowPage = searchResultPage.addToBasket(mySelectedBook);
    }

    @Given("I select {string} in basket pop-up")
    public void i_select_in_basket_pop_up(String basketAddButton) {
        confirmWindowPage.getButton(basketAddButton);
        basketPage = confirmWindowPage.navigateToBasketPage();
    }

    @Given("I am redirected to a basket page with name {string}")
    public void i_am_redirected_to_a_basket_page_with_name(String basketPageName) {
        Assertions.assertEquals(basketPageName, basketPage.getTITLE());
    }

    @Given("Basket order summary is as following:")
    public void basket_order_summary_is_as_following(Map<String, String> orderSummary) {
        String deliveryCost = orderSummary.get("Delivery cost");
        String deliveryActualResult = basketPage.checkDataOfDeliveryCost();
        Assertions.assertEquals(deliveryCost, deliveryActualResult);
        String totalSummary = orderSummary.get("Total");
        String totalActualResult = basketPage.checkDataOfTotal();
        Assertions.assertEquals(totalSummary, totalActualResult);
    }

    @Given("I click {string} button on Basket page")
    public void i_click_button_on_page(String checkOutButton) {
        basketPage.getButton(checkOutButton);
        paymentPage = basketPage.navigateToPaymentPage();
    }

    @Given("I checkout as a new customer with email {string}")
    public void i_checkout_as_a_new_customer_with_email(String email) {
        paymentPage.setEmail(email);
    }
    @And("I checkout as a new customer with of country {string} phone number {string}{string}")
    public void iCheckoutAsANewCustomerWithPhoneNumber(String country, String code, String number) {
        paymentPage.setPhoneCode(country+" "+code);
        paymentPage.setPhoneNumber(number);
    }

    @Given("Checkout order summary is as following:")
    public void checkout_order_summary_is_as_following(Map<String,String> orderSummary) {
        String subTotal = orderSummary.get("Sub-total");
        String subTotalActualResult = paymentPage.checkDataOfSubTotalOnPaymentPage();
        Assertions.assertEquals(subTotal, subTotalActualResult);
        String delivery = orderSummary.get("Delivery country");
        String deliveryActualResult = paymentPage.checkDataOfDeliveryOnPaymentPage();
        Assertions.assertEquals(delivery, deliveryActualResult);
        String vat = orderSummary.get("VAT");
        String vatActualResult = paymentPage.checkDataOfVatOnPaymentPage();
        Assertions.assertEquals(vat, vatActualResult);
        String total = orderSummary.get("Total");
        String totalActualResult = paymentPage.checkDataOfTotalOnPaymentPage();
        Assertions.assertEquals(total, totalActualResult);
    }

    @And("I fill delivery address information manually:")
    public void iFillDeliveryAddressInformationManually(Map<String,String> delivAddress) {
        String fullAddress = delivAddress.get("Full name");
        paymentPage.setFullNameDeliveryAddress(fullAddress);
        String country = delivAddress.get("Delivery country");
        paymentPage.setDeliveryCountry(country);
        String addressLine1 = delivAddress.get("Address line 1");
        paymentPage.setAddressLine1(addressLine1);
        String addressLine2 = delivAddress.get("Address line 2");
        paymentPage.setAddressLine2(addressLine2);
        String cityTown = delivAddress.get("Town/City");
        paymentPage.setTownCity(cityTown);
        String countryState = delivAddress.get("Country/State");
        paymentPage.setCountyState(countryState);
        String postCode = delivAddress.get("Postcode");
        paymentPage.setPostcode(postCode);
        basePageInit.tearDown();
    }
}
