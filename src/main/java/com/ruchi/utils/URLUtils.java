package com.ruchi.utils;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class URLUtils {

    final static Logger logger = Logger.getLogger(URLUtils.class);

    // method to get api url
    public String getAPIURL(String resource, String searchParameter) {
        StringBuilder url = new StringBuilder(PropertyReader.API_URL);
        if (resource.equals("search")) {
            url.append(PropertyReader.SEARCH_ENDPOINT);
            url.append("?apiKey=");
            url.append(PropertyReader.API_KEY);
            url.append("&format=json&numItems=1&query=");
            url.append(searchParameter);
        } else if (resource.equals("nbp")) {
            url.append(PropertyReader.RECOMMENDATION_ENDPOINT);
            url.append("?apiKey=");
            url.append(PropertyReader.API_KEY);
            url.append("&format=json&itemId=");
            url.append(searchParameter);
        } else if (resource.equals("reviews")) {
            url.append(PropertyReader.REVIEW_ENDPOINT);
            url.append("/");
            url.append(searchParameter);
            url.append("?apiKey=");
            url.append(PropertyReader.API_KEY);
            url.append("&format=json");
        }
        return url.toString();
    }

    // method to request the url and get json response
    public String getJSONResponse(String url) {
        try {
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String outputLine = new String();
                StringBuilder response = new StringBuilder();

                while ((outputLine = in.readLine()) != null) {
                    response.append(outputLine);
                }
                in.close();
                return response.toString();
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("Error in url utils: ", e);
            return null;
        }
    }
}
