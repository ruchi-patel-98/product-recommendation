package com.ruchi.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruchi.models.Product;
import com.ruchi.models.ProductResults;
import com.ruchi.models.RecommendedProduct;
import com.ruchi.utils.RequestTask;
import com.ruchi.utils.URLUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

class RecommendationEngine {

    final static private Logger logger = Logger.getLogger(RecommendationEngine.class);

    final private int concurrentAPIRequest = 5;
    final private int noOfProductRecommendation = 10;
    final private ObjectMapper objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    final private URLUtils urlUtils;

    public RecommendationEngine(URLUtils urlUtils) {
        this.urlUtils = urlUtils;
    }

    // Method to get recommended products
    public List<RecommendedProduct> getRecommendedProducts(String product) {

        List<RecommendedProduct> recommendedProducts = new ArrayList<RecommendedProduct>();
        logger.info("In getRecommendedProducts");

        Long itemId = this.getFirstItemId(product);
        if (itemId != null) {
            logger.info(itemId);
            List<Product> items = this.getRecommendedProducts(itemId);
            if (items == null)
                items = new ArrayList<Product>();
            int size = Math.min(noOfProductRecommendation, items.size());

            // Code to make concurrent requests
            ExecutorService requestTaskExecutor = Executors.newFixedThreadPool(concurrentAPIRequest);
            CompletionService<RecommendedProduct> requestTaskCompletionService =
                new ExecutorCompletionService<RecommendedProduct>(requestTaskExecutor);

            int taskCounter = 0;
            for (taskCounter = 0; taskCounter < size; taskCounter++) {
                RecommendedProduct rp = new RecommendedProduct();
                Product item = items.get(taskCounter);
                rp.setItemId(item.getItemId());
                rp.setItemName(item.getName());
                requestTaskCompletionService.submit(new RequestTask(rp, objectMapper, urlUtils));
            }

            for (int completedTask = 0; completedTask < taskCounter; completedTask++) {
                try {
                    Future<RecommendedProduct> requestResult = requestTaskCompletionService.take();
                    RecommendedProduct rp = requestResult.get();
                    recommendedProducts.add(rp);
                } catch (Exception e) {
                    logger.error("cause:" + e.getCause());
                    logger.error("Error in task completion: ", e);
                }
            }
            logger.info("Before sorting:");
            logger.info(recommendedProducts);
            logger.info("After sorting:");
            Collections.sort(recommendedProducts);
            logger.info(recommendedProducts);
        }
        return recommendedProducts;
    }

    // method to get the item id from api
    protected Long getFirstItemId(String product) {
        logger.info("In getItemId");
        logger.info("Searching the product");
        String url = this.urlUtils.getAPIURL("search", product);
        logger.info("Making serach request");
        String jsonResponse = urlUtils.getJSONResponse(url);
        Long itemId = null;
        if (jsonResponse != null) {
            try {
                ProductResults productResults = objectMapper.readValue(jsonResponse, ProductResults.class);
                List<Product> items = productResults != null ? productResults.getItems() : null;
                if (items != null && !items.isEmpty()) {
                    itemId = items.get(0).getItemId();
                }
            } catch (JsonProcessingException e) {
                // silence error
                logger.error(e);
                e.printStackTrace();
            }
        }
        return itemId;
    }

    // method to get the recommended items from api
    protected List<Product> getRecommendedProducts(long itemId) {
        logger.info("In getRecommendedProducts");
        logger.info("Searching for the recommended products");
        String url = this.urlUtils.getAPIURL("nbp", "" + itemId);
        logger.info("Making recommendation request");
        String json = urlUtils.getJSONResponse(url);
        List<Product> items = null;
        if (json != null) {
            try {
                items = objectMapper.readValue(json, new TypeReference<List<Product>>(){});
            } catch (JsonProcessingException e) {
                // silence error
            }
        }
        return items;
    }
}
