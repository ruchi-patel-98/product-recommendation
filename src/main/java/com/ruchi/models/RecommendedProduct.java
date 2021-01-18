package com.ruchi.models;


import java.util.List;

import com.ruchi.utils.PropertyReader;

public class RecommendedProduct implements Comparable<RecommendedProduct> {
    private long itemId;
    private String itemName;
    private float averageOverallRating;
    private float overallRatingRange;
    private int totalReviewCount;
    private List<String> reviewList;

    // Method to conunt the keywords in review List
    public int getKeywordCount(String keywordString) {
        int count = 0;
        String[] keywords = keywordString.split(",");
        for (String review : reviewList) {
            for (int i = 0; i < keywords.length; i++) {
                if (review.contains(keywords[i].trim()))
                    count++;
            }
        }
        return count;
    }

    public String toString() {
        return ("Item Number = " + this.getItemId() + " and  Item Name = " + this.getItemName());
    }

    // Method to rank the products
    public int compareTo(RecommendedProduct rp) {
        float score1 = 0;
        float score2 = 0;
        float avg1 = (this.getOverallRatingRange() + this.getAverageOverallRating()) / 2;
        float avg2 = (rp.getOverallRatingRange() + rp.getAverageOverallRating()) / 2;
        score1 += avg1;
        score2 += avg2;
        score1 += 0.01 * this.getTotalReviewCount();
        score2 += 0.01 * rp.getTotalReviewCount();
        score1 += this.getKeywordCount(PropertyReader.POSITIVE_KEYWORDS);
        score2 += rp.getKeywordCount(PropertyReader.POSITIVE_KEYWORDS);
        score1 -= this.getKeywordCount(PropertyReader.NEGATIVE_KEYWORDS);
        score2 -= rp.getKeywordCount(PropertyReader.NEGATIVE_KEYWORDS);
        if (score1 > score2)
            return 1;
        else if (score1 < score2)
            return -1;
        return 0;
    }

    public long getItemId() {
        return this.itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getAverageOverallRating() {
        return this.averageOverallRating;
    }

    public void setAverageOverallRating(float averageOverallRating) {
        this.averageOverallRating = averageOverallRating;
    }

    public float getOverallRatingRange() {
        return this.overallRatingRange;
    }

    public void setOverallRatingRange(float overallRatingRange) {
        this.overallRatingRange = overallRatingRange;
    }

    public int getTotalReviewCount() {
        return this.totalReviewCount;
    }

    public void setTotalReviewCount(int totalReviewCount) {
        this.totalReviewCount = totalReviewCount;
    }

    public List<String> getReviewList() {
        return this.reviewList;
    }

    public void setReviewList(List<String> reviewList) {
        this.reviewList = reviewList;
    }
}
