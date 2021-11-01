@regression @smoke @MB2P-122 @ui
Feature: Validating pizza application functionalities

  Scenario Outline: Validating Place Order functionality
    Given user navigates to pizza application
    When user creates pizza order with data
      | Pizza        | <Pizza>        |
      | Toppings 1   | <Toppings 1>   |
      | Toppings 2   | <Toppings 2>   |
      | Quantity     | <Quantity>     |
      | Name         | <Name>         |
      | Email        | <Email>        |
      | Phone        | <Phone>        |
      | Payment Type | <Payment Type> |
    Then user validates that order is created with success message "Thank you for your order! TOTAL: " for "<Quantity>" "<Pizza>"
    Examples:
      | Pizza                        | Toppings 1 | Toppings 2   | Quantity | Name        | Email                | Phone     | Payment Type   |
      | Small 6 Slices - no toppings | Mushrooms  | Extra cheese | 1        | John Doe    | johndoe@gmail.com    | 123456789 | Cash on Pickup |
      | Large 10 Slices - 2 toppings | Mushrooms  | Extra cheese | 3        | Patel Harsh | patelharsh@gmail.com | 123456789 | Credit Card    |
      | Medium 8 Slices - 2 toppings | Olives     | Diced Mango  | 5        | Patel Harsh | patelharsh@gmail.com | 123456789 | Credit Card    |

  # Test Scenario -> placing an order without providing name and with invalid names
  # Test scenario -> without choosing pizza
  # Test scenario -> Without choosing payment option







