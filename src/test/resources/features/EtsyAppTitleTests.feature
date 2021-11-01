@regression @ui @MB2P-114
Feature: Validating etsy titles

  Scenario Outline: Validating etsy separate page titles
    Given user navigates to Etsy application
    When user clicks on "<Section>" section
    Then user validates title is "<Title>"
    Examples:
      | Title                         | Section                 |
      | Jewelry & Accessories \| Etsy | Jewelry and Accessories |
      | Clothing & Shoes \| Etsy      | Clothing and Shoes      |
      | Home & Living \| Etsy         | Home and Living         |
      | Wedding & Party \| Etsy       | Wedding and Party       |
      | Toys & Entertainment \| Etsy  | Toys and Entertainment  |
      | Art & Collectibles \| Etsy    | Art and Collectibles    |
