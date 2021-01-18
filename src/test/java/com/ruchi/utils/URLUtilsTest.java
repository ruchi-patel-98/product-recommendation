package com.ruchi.utils;

import junit.framework.TestCase;
import org.junit.Test;


public class URLUtilsTest extends TestCase{

    @Test
    public void testGetAPIURL() {
        PropertyReader.initializeProperties();

        assertEquals("http://api.product.com/v1/"+PropertyReader.SEARCH_ENDPOINT
                      +"?apiKey="+PropertyReader.API_KEY+"&format=json&numItems=1&query=hello", 
                      new URLUtils().getAPIURL("search", "hello"));
        assertEquals("http://api.product.com/v1/"+PropertyReader.RECOMMENDATION_ENDPOINT
                      +"?apiKey="+PropertyReader.API_KEY+"&format=json&itemId=hello", 
                      new URLUtils().getAPIURL("nbp", "hello"));
        assertEquals("http://api.product.com/v1/"+PropertyReader.REVIEW_ENDPOINT
                      +"/hello?apiKey="+PropertyReader.API_KEY+"&format=json", 
                      new URLUtils().getAPIURL("reviews", "hello"));
    }
}