Feature: Desktop Checkout for Guest User
  As a customer
  I want to be able proceed to checkout
  So that I can specify my delivery and payment details and place the order

  Scenario: Proceed to checkout, final review and place order as guest user
    Given I am an anonymous customer with clear cookies
    And I open the "Initial home page" of "https://www.bookdepository.com/"
    And I search for "Thinking in Java"
    And I am redirected to a "Search page"
    And Search results contain the following products
      | Thinking in Java                                                  |
      | Think Java                                                        |
      | Thinking Recursively - A 20th Anniversary Edition with Java (WSE) |
    And I apply the following search filters
      | Price range  | 30€ +          |
      | Availability | In Stock (5)   |
      | Language     | English (17)   |
      | Format       | Paperback (22) |
    And Search results contain only the following products
      | Thinking in Java                                                  |
      | Think Java                                                        |
      | Thinking Recursively - A 20th Anniversary Edition with Java (WSE) |
      | Think Data Structures                                             |
    And I click 'Add to basket' button for product with name "Thinking in Java"
    And I select "Basket / Checkout" in basket pop-up
    And I am redirected to a basket page with name "Basket page"
    And Basket order summary is as following:
      | Delivery cost | FREE    |
      | Total         | 85,38 € |
    And I click 'Checkout' button on Basket page
    And I checkout as a new customer with email "TestForAutomation.777@gmail.com"
    And I checkout as a new customer with of country "Andorra" phone number "+376""345-123"
    And Checkout order summary is as following:
      | Sub-total        | 85,38 € |
      | Delivery country | FREE    |
      | VAT              | 0,00 €  |
      | Total            | 85,38 € |
    And I fill delivery address information manually:
      | Full name        | Olha Baht        |
      | Delivery country | Andorra          |
      | Address line 1   | San Jose str.    |
      | Address line 2   | app.12           |
      | Town/City        | Andorra la Vieja |
      | Country/State    | Andorra          |
      | Postcode         | AD500            |
