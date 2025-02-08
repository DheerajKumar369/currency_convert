# Currency Converter API

This is a Spring Boot application that integrates with a public API to provide real-time
currency conversion functionality. It includes endpoints to fetch exchange rates and convert
currency amounts.

# Technologies Used

- Java 21
- Spring Boot
- Maven
- External API: Exchange Rates API (or any other public API of your choice)

# Features

1. GET /api/currency-rates?base=USD: Fetch exchange rates for the specified base currency (default is USD).

2. POST /api/convert-currency: Convert an amount from one currency to another.
   
# Installation

## Prerequisites
  Ensure you have the following installed:<br>
    * Java 21 or higher<br>
    * Maven<br>
    * Git (for cloning the repository)

## Steps to Run the Application Locally

  - Clone the repository:git clone https://github.com/yourusername/currency-converter.git
  - Navigate to the project directory: cd currency-converter

1. Configure the API key:
   
	- Sign up for an API key from the external API provider (e.g., Exchange RatesAPI).
	- Add your API key to the configuration.
	- Run the application:

2. Access the application: The application should now be running locally at http://localhost:8080.
   
# API Endpoints

  1. GET /api/currency-rates<br>
      Fetch exchange rates for the specified base currency.<br>
   Query Parameters:
     -  base: (optional, default: "USD") Base currency.

## Request Example:
  http<br>
  CopyEdit<br>
	  - GET /api/currency-rates?base=USD
 
## Response Example:
   ``` json<br>
  
    {
      "USD": 1.0,
      "EUR": 0.9,
      "JPY": 110.0
    }

  ```
2. POST /api/convert-currency<br>
  Convert an amount from one currency to another.

## Request Body:
```
  {
    "from": "USD",
      "to": "EUR",
      "amount": 100
    }
   ``` 
## Response Example:
 ``` 
  {
    "from": "USD",
    "to": "EUR",
    "amount": 100,
    "convertedAmount": 94.5
  }
```
  
## Error Handling
  - 400 Bad Requests : Invalid or missing parameters.
  - 404 Not Found: Currency code not recognized.
  - 500 Internal Server Error: External API failure or unexpected error.

 # Currency Converter API Documentation
  
  1. GET /api/currency-rates
    - Fetches the exchange rates for the given base currency. If the base currency is not provided, it defaults to USD.
 
 Query Parameters:
    - Base (optional, default: "USD"): The base currency to get exchange rates for.
    
 ## Request Example:
  - GET /api/currency-rates?base=USD
  
## Response Example:
  ```
  {
    "FJD": 2.3047511886560885,
    "MXN": 20.26575697017241,
    "CLF": 0.03578947569339067,
    "BR" : 5.926100998070283
  }
```
2. POST /api/convert<br>
    Description: Converts a specified amount from one currency to another using the fetched exchange rates.

## Request Example:

  - GET /api/convert-currency
     ```
      Request Body:
        {
         "from": "USD",
         "to": "INR",
         "amount": 3
        }
     ```
## Response Example:
  
  ```
  {
    "from": "USD",
    "to": "INR",
    "amount": 3.0,
    "convertedAmount": 258.76651457759414
  }
````
## Response Status Codes:
  - 200 OK : Successfully fetched exchange rates.
  - 400 Bad Requests : Invalid or missing base currency.
  - 500 Internal Server Error : API failure (e.g., external service unavailable).

  
