package com.ruchi.models;

public class ReviewStatistics {
    private Float averageOverallRating;
    private Float overallRatingRange;
    private Integer totalReviewCount;

    public Float getAverageOverallRating() {
        return averageOverallRating;
    }

    public void setAverageOverallRating(Float averageOverallRating) {
        this.averageOverallRating = averageOverallRating;
    }

    public Float getOverallRatingRange() {
        return overallRatingRange;
    }

    public void setOverallRatingRange(Float overallRatingRange) {
        this.overallRatingRange = overallRatingRange;
    }

    public Integer getTotalReviewCount() {
        return totalReviewCount;
    }

    public void setTotalReviewCount(Integer totalReviewCount) {
        this.totalReviewCount = totalReviewCount;
    }
}
