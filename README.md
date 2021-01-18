# Product Recommendation #

This command line app asks user for initial search input. It searches the given input using product search API.
From the search results, it will select first product and it will make another API call to get recommended product for 
selected product. Once we have list of recommended products, it will make another API call for each of the recommended 
product to retrieve reviews. After reviews are retrieved it applies basic sentiment analysis to rank recommended products.

## Technologies Used:
- Java
- Concurrency
- Jackson for JSON parsing
- Unit tests (jUnit & Mockito)
- Basic sentiment analysis method

## Enhancements:
- Use of third party HTTP Client library