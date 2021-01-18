package com.ruchi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruchi.models.RecommendedProduct;
import com.ruchi.models.Review;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;

public class RequestTask implements Callable<RecommendedProduct> {

    final static Logger logger = Logger.getLogger(RequestTask.class);

    private RecommendedProduct rp;
    private ObjectMapper objectMapper;
    private URLUtils urlUtils;


    public RequestTask(RecommendedProduct rp, ObjectMapper objectMapper, URLUtils urlUtils) {
        this.rp = rp;
        this.objectMapper = objectMapper;
        this.urlUtils = urlUtils;
    }

    // task to executed to get the reviews for the item
    // concurrent execution supported
    public RecommendedProduct call() throws Exception {

        logger.info("Getting data for " + this.rp.getItemId());

        String url = urlUtils.getAPIURL("reviews", "" + this.rp.getItemId());
        String json = urlUtils.getJSONResponse(url);

        if (json == null) {
            logger.error("nothing was retrieved for itemId: " + this.rp.getItemId());
        } else {
            try {
                Review review = objectMapper.readValue(json, Review.class);
                if (review != null) {

                    this.rp.setReviewList(review.getReviewTexts());

                    if (review.getReviewStatistics() != null) {
                        this.rp.setTotalReviewCount(review.getReviewStatistics().getTotalReviewCount());
                        this.rp.setAverageOverallRating(review.getReviewStatistics().getAverageOverallRating());
                        this.rp.setOverallRatingRange(review.getReviewStatistics().getOverallRatingRange());
                    } else {
                        this.rp.setTotalReviewCount(0);
                        this.rp.setAverageOverallRating(0);
                        this.rp.setOverallRatingRange(0);
                    }
                }
            } catch (JsonProcessingException e) {
                // silence error
            }
        }
        return this.rp;
    }
}